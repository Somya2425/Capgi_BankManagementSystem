package com.example.Capgi_BankManagementSystem.dto.response;

import lombok.Data;

@Data
public class AccountResponseDto {

    private Integer id;
    private String type;
    private double balance;
    private boolean active;

    private Integer customerId;
}