package com.springBank.bankApp.model;

import org.hibernate.annotations.UuidGenerator;
import static jakarta.persistence.EnumType.STRING;
import com.springBank.bankApp.enums.OperationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "operation_history")
public class OperationHistory {

    @Id
    @Column(name = "operation_id")
    @GeneratedValue
    @UuidGenerator
    private String operationId;
    
    @Enumerated(STRING)
    @Column(nullable = false)
    private OperationType operationType;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private BankAccount bankAccount;

    @Column(nullable = false)
    private String operationTime;
}
