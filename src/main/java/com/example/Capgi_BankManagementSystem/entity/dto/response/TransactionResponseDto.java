package com.example.Capgi_BankManagementSystem.entity.dto.response;


import com.example.Capgi_BankManagementSystem.enums.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TransactionResponseDto {
    private Integer txnId;
    private TransactionType type;
    private double amount;
    private LocalDateTime date;
    private Integer accountId;
}
