
package com.example.Capgi_BankManagementSystem.entity;

import com.example.Capgi_BankManagementSystem.enums.AccountType;
import jakarta.persistence.*;

import java.util.List;

@Entity

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private AccountType accountType;
    private double balance;

    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Loan> loans;


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
