package com.example.Capgi_BankManagementSystem.entity;

import com.example.Capgi_BankManagementSystem.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer txnId;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private double amount;
    private LocalDateTime date;

    @ManyToOne
    private Account account;
}