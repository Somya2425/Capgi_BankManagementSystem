package com.example.Capgi_BankManagementSystem.repository;

import com.example.Capgi_BankManagementSystem.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    // Mini statement
    List<Transaction> findByAccountId(Integer accountId);
}