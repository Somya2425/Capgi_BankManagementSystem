package com.example.Capgi_BankManagementSystem.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoanRequestDto {

    @NotNull(message = "Account ID is required")
    private Integer accountId;

    @NotNull(message = "Loan amount is required")
    private Double amount;
}