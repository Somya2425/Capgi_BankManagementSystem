package com.example.Capgi_BankManagementSystem.dto.request;

import com.example.Capgi_BankManagementSystem.enums.AccountType;
import lombok.Data;

@Data
public class AccountRequestDto {
    private AccountType type;
    private double balance;
}
