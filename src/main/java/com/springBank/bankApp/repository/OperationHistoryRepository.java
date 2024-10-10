package com.springBank.bankApp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springBank.bankApp.model.OperationHistory;

public interface OperationHistoryRepository extends JpaRepository<OperationHistory, String> {
    @Query("SELECT ba FROM OperationHistory ba WHERE ba.bankAccount.bankAccountId = :accountId")
    List<OperationHistory> getAllOperationHistoryByAccountId(String accountId);
}
