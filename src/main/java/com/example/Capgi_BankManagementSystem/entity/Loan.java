package com.example.Capgi_BankManagementSystem.entity;

import com.example.Capgi_BankManagementSystem.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loanId;

    private double amount;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    /**
     * Many loans → One account
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
}