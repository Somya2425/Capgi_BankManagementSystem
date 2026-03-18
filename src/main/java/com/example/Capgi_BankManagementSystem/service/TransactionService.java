package com.example.Capgi_BankManagementSystem.service;


import com.example.Capgi_BankManagementSystem.entity.dto.response.TransactionResponseDto;

import java.util.List;

public interface TransactionService {

    TransactionResponseDto deposit(Integer accountId, double amount);

    TransactionResponseDto withdraw(Integer accountId, double amount);

    void transfer(Integer fromId, Integer toId, double amount);

    List<TransactionResponseDto> getMiniStatement(Integer accountId);
}
