package com.springBank.bankApp.service.impl;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import com.springBank.bankApp.enums.OperationType;
import com.springBank.bankApp.model.BankAccount;
import com.springBank.bankApp.model.OperationHistory;
import com.springBank.bankApp.repository.OperationHistoryRepository;
import com.springBank.bankApp.service.OperationHistoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OperationHistoryServiceImpl implements OperationHistoryService{

    private final OperationHistoryRepository operationHistoryRepository;

    public List<OperationHistory> getFullHistory() {
        return operationHistoryRepository.findAll();
    }

    public List<OperationHistory> getAllHistoryByClientId(String id) {
        return operationHistoryRepository.getAllOperationHistoryByAccountId(id);
    }

    public void createOperationHistory(OperationType operationType, BankAccount bankAccount) {

        LocalDateTime now = LocalDateTime.now();
        OperationHistory operationHistory = OperationHistory.builder()
            .operationType(operationType)
            .bankAccount(bankAccount)
            .operationTime(now.toString())
            .build();
        operationHistoryRepository.save(operationHistory);
    }
    
}
