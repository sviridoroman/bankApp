package com.springBank.bankApp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springBank.bankApp.enums.OperationType;
import com.springBank.bankApp.exception.AccountNotFoundException;
import com.springBank.bankApp.exception.ExceptionEnum;
import com.springBank.bankApp.exception.IncorrectPinException;
import com.springBank.bankApp.model.BankAccount;
import com.springBank.bankApp.model.Client;
import com.springBank.bankApp.repository.BankAccountRepository;
import com.springBank.bankApp.repository.ClientRepository;
import com.springBank.bankApp.service.BankAccountService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BankAccountServiceImpl implements BankAccountService{

    private final BankAccountRepository bankAccountRepository;
    private final ClientRepository clientRepository;
    private final OperationHistoryServiceImpl operationHistoryService;

    private Client findClient(String accountName) {
        Optional<Client> client = clientRepository.findOneByName(accountName);
        if (!client.isPresent()) {
            throw new AccountNotFoundException(ExceptionEnum.ACCOUNT_NOT_FOUND.getMessage());
        }
        return client.get();
    }

    private boolean checkPin(String accountId, int pin) {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).get();
        if (bankAccount.getPin() == pin)
            return true;
        else return false;
    }

    private BankAccount findAccountById(String accountId) {
        Optional<BankAccount> account = bankAccountRepository.findById(accountId);
        if (!account.isPresent()) {
            throw new AccountNotFoundException(ExceptionEnum.ACCOUNT_NOT_FOUND.getMessage());
        }
        return account.get();
    }

    public BankAccount createAccount(String accountName, int pin) {
        Client client = findClient(accountName);
        BankAccount bankAccount = BankAccount.builder()
            .client(client)
            .pin(pin)
            .build();
        return bankAccountRepository.save(bankAccount);
    }

    public Double getBalance(String accountId, int pin) {
        BankAccount account = findAccountById(accountId);
        if(!checkPin(account.getBankAccountId(), pin)) {
            throw new IncorrectPinException(ExceptionEnum.INCORRECT_PINCODE.getMessage());
        }
        operationHistoryService.createOperationHistory(OperationType.GET_BALANCE, account);
        return account.getBalance();
    }

    public void addMoney(String accountId, int pin, Double quantity) {
        BankAccount account = findAccountById(accountId);
        if(!checkPin(account.getBankAccountId(), pin)) {
            throw new IncorrectPinException(ExceptionEnum.INCORRECT_PINCODE.getMessage());
        }
        account.setBalance(account.getBalance() + quantity);
        operationHistoryService.createOperationHistory(OperationType.ADD_MONEY, account);
        bankAccountRepository.save(account);
    }

    public void getMoney(String accountId, int pin, Double quantity) {
        BankAccount account = findAccountById(accountId);
        if(!checkPin(account.getBankAccountId(), pin)) {
            throw new IncorrectPinException(ExceptionEnum.INCORRECT_PINCODE.getMessage());
        }
        account.setBalance(account.getBalance() - quantity);
        operationHistoryService.createOperationHistory(OperationType.GET_MONEY, account);
        bankAccountRepository.save(account); 
    }

    public void sentMoneyToAnotherAccount(String accountId, int pin, Double quantity, String anotherAccountId) {
        BankAccount account = findAccountById(accountId);
        if(!checkPin(account.getBankAccountId(), pin)) {
            throw new IncorrectPinException(ExceptionEnum.INCORRECT_PINCODE.getMessage());
        }
        BankAccount anotherAccount = findAccountById(anotherAccountId);
        account.setBalance(account.getBalance() - quantity);
        operationHistoryService.createOperationHistory(OperationType.SEND_MONEY_TO_ANOTHER_ACCOUNT, account);
        bankAccountRepository.save(account); 
        anotherAccount.setBalance(anotherAccount.getBalance() + quantity);
        operationHistoryService.createOperationHistory(OperationType.ADD_MONEY, anotherAccount);
        bankAccountRepository.save(anotherAccount);        
    }

    public BankAccount getAccount(String accountId) {
        return findAccountById(accountId);
    }

    public List<BankAccount> getAllAccounts() {
        return bankAccountRepository.findAll();
    }

    public List<BankAccount> getAllAccountsByClientId(String clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if (!client.isPresent()) {
            throw new AccountNotFoundException(ExceptionEnum.ACCOUNT_NOT_FOUND.getMessage());
        }
        return bankAccountRepository.getAllAccountsByClientId(clientId);
    }   
}
