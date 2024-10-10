package com.springBank.bankApp.service.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.springBank.bankApp.exception.AccountNotFoundException;
import com.springBank.bankApp.exception.ExceptionEnum;
import com.springBank.bankApp.model.Client;
import com.springBank.bankApp.repository.ClientRepository;
import com.springBank.bankApp.service.ClientService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    public Client register(String name) {
        Client client = Client.builder()
            .name(name)
            .build();
        clientRepository.save(client);
        return client;
    }

    public Client getClientById(String clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if (!client.isPresent()) {
            throw new AccountNotFoundException(ExceptionEnum.ACCOUNT_NOT_FOUND.getMessage());
        }
        return client.get();
    }

    public Client getClientByName(String name) {
        Optional<Client> client = clientRepository.findOneByName(name);
        if (!client.isPresent()) {
            throw new AccountNotFoundException(ExceptionEnum.ACCOUNT_NOT_FOUND.getMessage());
        }
        return client.get();
    }
    
}
