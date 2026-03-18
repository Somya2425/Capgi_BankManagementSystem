package com.example.Capgi_BankManagementSystem.entity;

import com.example.Capgi_BankManagementSystem.enums.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private double balance;

    private boolean active = true;

    /**
     * Many accounts → One customer
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * One account → Many transactions
     */
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    /**
     * One account → Many loans
     */
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Loan> loans;
}