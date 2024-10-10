package com.springBank.bankApp.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springBank.bankApp.controller.ClientController;
import com.springBank.bankApp.dto.request.ClientNameDto;
import com.springBank.bankApp.dto.request.ClientRegisterDto;
import com.springBank.bankApp.model.Client;
import com.springBank.bankApp.service.impl.ClientServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name="Client Controller", description="контроллер по работе с клиентами")
@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientControllerImpl implements ClientController{

    private final ClientServiceImpl clientServiceImpl;

    @Operation(
	summary = "Регистрация пользователя",
	description = "Позволяет зарегистрировать пользователя"
    )
    @PostMapping
    public ResponseEntity<Client> register(@RequestBody ClientNameDto name) {
        return ResponseEntity.ok(clientServiceImpl.register(name.getName()));
    }

    @Operation(
	summary = "получить пользователя",
	description = "получить пользователя по ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable @Parameter(description = "Id пользователя") String id) {
        return ResponseEntity.ok(clientServiceImpl.getClientById(id));
    }
    @Operation(
	summary = "получить пользователя",
	description = "получить пользователя по имени"
    )
    @GetMapping("/name")
    public ResponseEntity<Client> getClientByName(@RequestBody ClientNameDto name) {
        return ResponseEntity.ok(clientServiceImpl.getClientByName(name.getName()));
    }
    
}
