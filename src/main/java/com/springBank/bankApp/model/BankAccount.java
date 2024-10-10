package com.springBank.bankApp.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bank_account")
public class BankAccount {
    
    @Id
    @Column(name = "account_id")
    @GeneratedValue
    @UuidGenerator
    private String bankAccountId;
    
    @Column(nullable = false)
    private int pin;

    @Column
    @Default()
    private Double balance = Double.valueOf(0);

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client client;
}
