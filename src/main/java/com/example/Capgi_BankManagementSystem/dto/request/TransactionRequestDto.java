package com.example.Capgi_BankManagementSystem.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransactionRequestDto {

    private Integer accountId;      // for deposit/withdraw

    private Integer fromAccountId; // for transfer
    private Integer toAccountId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than 0")
    private Double amount;
}