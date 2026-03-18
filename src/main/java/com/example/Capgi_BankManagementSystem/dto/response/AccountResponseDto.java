package com.example.Capgi_BankManagementSystem.dto.response;

import com.example.Capgi_BankManagementSystem.enums.AccountType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountResponseDto {
    private Integer accountId;
    private AccountType type;
    private double balance;
    private Boolean active;
    private Integer customerId;
}
