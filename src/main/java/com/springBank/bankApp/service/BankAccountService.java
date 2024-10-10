package com.springBank.bankApp.service;

import java.util.List;

import com.springBank.bankApp.model.BankAccount;

public interface BankAccountService {
    public BankAccount createAccount(String accountId, int pin);
    public BankAccount getAccount(String accountId);
    public List<BankAccount> getAllAccounts();
    public List<BankAccount> getAllAccountsByClientId(String clientId);
    public Double getBalance(String accountId, int pin);
    public void addMoney(String accountId, int pin, Double quantity);
    public void getMoney(String accountId, int pin, Double quantity);
    public void sentMoneyToAnotherAccount(String accountId, int pin, Double quantity, String anotherAccountId);
}
