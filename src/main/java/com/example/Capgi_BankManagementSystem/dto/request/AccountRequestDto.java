package com.example.Capgi_BankManagementSystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountRequestDto {

    @NotBlank(message = "Account type is required (SAVINGS/CURRENT)")
    private String type;

    @NotNull(message = "Initial balance is required")
    private Double balance;
}