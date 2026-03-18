package com.example.Capgi_BankManagementSystem.entity.dto.request;


import lombok.Data;

@Data
public class TransactionRequestDto {
    private Integer fromAccountId;
    private Integer toAccountId;
    private double amount;
}
