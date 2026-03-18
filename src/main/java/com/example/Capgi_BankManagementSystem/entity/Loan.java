package com.example.Capgi_BankManagementSystem.entity;

import com.example.Capgi_BankManagementSystem.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
public class Loan {

    @Id
    private String loanId;

    private double amount;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    @ManyToOne
    private Account account;
}