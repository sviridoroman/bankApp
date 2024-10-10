package com.springBank.bankApp.controller.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springBank.bankApp.controller.BankAccountController;
import com.springBank.bankApp.dto.request.GetMoneyDto;
import com.springBank.bankApp.dto.request.NewAccountDto;
import com.springBank.bankApp.dto.request.PinDto;
import com.springBank.bankApp.dto.request.SendingMoneyDto;
import com.springBank.bankApp.model.BankAccount;
import com.springBank.bankApp.model.OperationHistory;
import com.springBank.bankApp.service.impl.BankAccountServiceImpl;
import com.springBank.bankApp.service.impl.OperationHistoryServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class BankAccountControllerImpl implements BankAccountController{
    
    private final BankAccountServiceImpl bankAccountService;
    private final OperationHistoryServiceImpl operationHistoryService;

    @Operation(
	summary = "Создание счета",
	description = "Создание счета по имени пользователя"
    )
    @PostMapping
    public ResponseEntity<BankAccount> createAccount(@RequestBody NewAccountDto newAccount) {
        return ResponseEntity
                .ok(bankAccountService.createAccount(newAccount.getClientName(), newAccount.getPin()));
    }

    @Operation(
        summary = "счет",
        description = "получение счета по ID"
        )
    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getAccountbyId(@PathVariable @Parameter(description = "Id счета") String id) {
        return ResponseEntity.ok(bankAccountService.getAccount(id));
    }

    @Operation(
        summary = "список всех счетов",
        description = "получение списка всех счетов"
        )
    @GetMapping
    public ResponseEntity<List<BankAccount>> getAllAccounts() {
        return ResponseEntity.ok(bankAccountService.getAllAccounts());
    }

    @Operation(
        summary = "проверка баланса",
        description = "возвращает баланс счета"
        )
    @GetMapping("/balance/{id}")
    public ResponseEntity<Double> getAccountBalance(@PathVariable @Parameter(description = "Id счета") String id, @RequestBody PinDto pin) {
        return ResponseEntity
            .ok(bankAccountService.getBalance(id, pin.getPin()));
    }

    @Operation(
        summary = "снятие денег",
        description = "снимает заданную сумму с счета"
        )
    @PutMapping("/get/{id}")
    public void getMoneyFromAccount(@PathVariable @Parameter(description = "Id счета") String id, @RequestBody GetMoneyDto getMoneyDto) {
        bankAccountService.getMoney(id, getMoneyDto.getPin(), Double.valueOf(getMoneyDto.getQuantity()));
    }

    @Operation(
        summary = "пополнение счета",
        description = "добавляет заданную сумму на счет"
        )
    @PutMapping("/add/{id}")
    public void addMoneyToAccount(@PathVariable @Parameter(description = "Id счета") String id, @RequestBody GetMoneyDto getMoneyDto) {
        bankAccountService.addMoney(id, getMoneyDto.getPin(), Double.valueOf(Double.parseDouble(getMoneyDto.getQuantity())));
    }

    @Operation(
        summary = "отправить на счет",
        description = "отправляет заданную сумму на другой счет"
        )
    @PutMapping("/send/{id}")
    public void SendToAnotherAccount(@PathVariable @Parameter(description = "Id счета") String id, @RequestBody SendingMoneyDto sendingMoneyDto) {
        bankAccountService.sentMoneyToAnotherAccount(id, sendingMoneyDto.getPin(), 
            Double.valueOf(sendingMoneyDto.getQuantity()), sendingMoneyDto.getAnotherAccountId());
    }

    @Operation(
        summary = "показать все счета пользователя",
        description = "возвращает счета указанного пользователя"
        )
    @GetMapping("/client/{id}")
    public ResponseEntity<List<BankAccount>> getAllAccountsbyClientId(@PathVariable @Parameter(description = "Id пользователя") String id) {
        return ResponseEntity.ok(bankAccountService.getAllAccountsByClientId(id));
    }

    @Operation(
        summary = "показать историю операций со счетом",
        description = "возвращает историю финансовых операций с указанным счетом"
        )
    @GetMapping("/history/{id}")
    public ResponseEntity<List<OperationHistory>> getAllHistorybyAccounttId(@PathVariable @Parameter(description = "Id счета") String id) {
        return ResponseEntity.ok(operationHistoryService.getAllHistoryByClientId(id));
    }
 
}
