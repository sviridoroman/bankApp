package com.springBank.bankApp.controller;

import org.springframework.http.ResponseEntity;

import com.springBank.bankApp.dto.request.ClientNameDto;
import com.springBank.bankApp.dto.request.ClientRegisterDto;
import com.springBank.bankApp.model.Client;

public interface ClientController {
    public ResponseEntity<Client> register(ClientNameDto name);
    public ResponseEntity<Client> getClientById(String clientId);
    public ResponseEntity<Client> getClientByName(ClientNameDto name);
}
