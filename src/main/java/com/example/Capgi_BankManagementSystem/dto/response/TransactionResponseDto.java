package com.example.Capgi_BankManagementSystem.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionResponseDto {

    private Integer txnId;
    private String type;
    private double amount;
    private LocalDateTime date;

    private Integer accountId;
}