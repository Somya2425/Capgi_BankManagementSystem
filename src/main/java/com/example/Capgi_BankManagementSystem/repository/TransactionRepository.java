package com.example.Capgi_BankManagementSystem.repository;


import com.example.Capgi_BankManagementSystem.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findTop10ByAccountIdOrderByDateDesc(Integer accountId);
}
