package com.springBank.bankApp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.springBank.bankApp.dto.request.GetMoneyDto;
import com.springBank.bankApp.dto.request.NewAccountDto;
import com.springBank.bankApp.dto.request.PinDto;
import com.springBank.bankApp.dto.request.SendingMoneyDto;
import com.springBank.bankApp.model.BankAccount;

public interface BankAccountController {
    public ResponseEntity<BankAccount> createAccount(NewAccountDto newAccountDto);
    public ResponseEntity<Double> getAccountBalance(String id, PinDto pin);
    public void getMoneyFromAccount(String id, GetMoneyDto getMoneyDto);
    public void addMoneyToAccount(String id, GetMoneyDto getMoneyDto);
    public void SendToAnotherAccount(String id, SendingMoneyDto sendingMoneyDto); 
    public ResponseEntity<BankAccount> getAccountbyId(String id);
    public ResponseEntity<List<BankAccount>> getAllAccounts();
    public ResponseEntity<List<BankAccount>> getAllAccountsbyClientId(String id);
}
