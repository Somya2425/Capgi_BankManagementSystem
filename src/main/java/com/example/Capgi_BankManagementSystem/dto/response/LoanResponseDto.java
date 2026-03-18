package com.example.Capgi_BankManagementSystem.dto.response;

import lombok.Data;

@Data
public class LoanResponseDto {

    private Integer loanId;
    private double amount;
    private String status;

    private Integer accountId;
}