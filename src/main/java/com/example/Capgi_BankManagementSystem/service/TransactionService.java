package com.example.Capgi_BankManagementSystem.service;

import com.example.Capgi_BankManagementSystem.dto.response.TransactionResponseDto;

import java.util.List;

public interface TransactionService {

    void deposit(Integer accountId, Double amount);

    void withdraw(Integer accountId, Double amount);

    void transfer(Integer fromId, Integer toId, Double amount);

    List<TransactionResponseDto> getTransactions(Integer accountId);
}