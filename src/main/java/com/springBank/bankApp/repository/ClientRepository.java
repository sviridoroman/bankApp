package com.springBank.bankApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBank.bankApp.model.Client;

public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findOneByName(String name);
}
