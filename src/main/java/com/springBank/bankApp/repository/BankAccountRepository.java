package com.springBank.bankApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springBank.bankApp.model.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    @Query("SELECT ba FROM BankAccount ba WHERE ba.client.clientId = :clientId")
    List<BankAccount> getAllAccountsByClientId(String clientId);
}
