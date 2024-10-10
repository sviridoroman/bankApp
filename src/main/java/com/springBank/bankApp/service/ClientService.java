package com.springBank.bankApp.service;

import com.springBank.bankApp.model.Client;

public interface ClientService {
    public Client register(String name);
    public Client getClientById(String clientId);
    public Client getClientByName(String name);
}
