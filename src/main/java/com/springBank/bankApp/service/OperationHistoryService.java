package com.springBank.bankApp.service;

import java.util.List;

import com.springBank.bankApp.enums.OperationType;
import com.springBank.bankApp.model.BankAccount;
import com.springBank.bankApp.model.OperationHistory;

public interface OperationHistoryService {
    public List<OperationHistory> getFullHistory();
    public List<OperationHistory> getAllHistoryByClientId(String id);
    public void createOperationHistory(OperationType operationType, BankAccount bankAccount);
}
