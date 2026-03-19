package com.example.Capgi_BankManagementSystem.controller;

import com.example.Capgi_BankManagementSystem.constants.ApiPaths;
import com.example.Capgi_BankManagementSystem.constants.AppConstants;
import com.example.Capgi_BankManagementSystem.dto.request.TransactionRequestDto;
import com.example.Capgi_BankManagementSystem.dto.response.ApiResponse;
import com.example.Capgi_BankManagementSystem.dto.response.TransactionResponseDto;
import com.example.Capgi_BankManagementSystem.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.TRANSACTION)
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    // ================= DEPOSIT =================
    @PostMapping("/deposit")
    public ApiResponse<String> deposit(@Valid @RequestBody TransactionRequestDto dto) {

        service.deposit(dto.getAccountId(), dto.getAmount());

        return ApiResponse.<String>builder()
                .status(AppConstants.STATUS_200)
                .message("Deposit successful")
                .build();
    }

    // ================= WITHDRAW =================
    @PostMapping("/withdraw")
    public ApiResponse<String> withdraw(@Valid @RequestBody TransactionRequestDto dto) {

        service.withdraw(dto.getAccountId(), dto.getAmount());

        return ApiResponse.<String>builder()
                .status(AppConstants.STATUS_200)
                .message("Withdrawal successful")
                .build();
    }

    // ================= TRANSFER =================
    @PostMapping("/transfer")
    public ApiResponse<String> transfer(@Valid @RequestBody TransactionRequestDto dto) {

        service.transfer(dto.getFromAccountId(), dto.getToAccountId(), dto.getAmount());

        return ApiResponse.<String>builder()
                .status(AppConstants.STATUS_200)
                .message("Transfer successful")
                .build();
    }

    // ================= MINI STATEMENT =================
    @GetMapping("/{accountId}")
    public ApiResponse<List<TransactionResponseDto>> getTransactions(@PathVariable Integer accountId) {

        return ApiResponse.<List<TransactionResponseDto>>builder()
                .status(AppConstants.STATUS_200)
                .message(AppConstants.MESSAGE_200)
                .data(service.getTransactions(accountId))
                .build();
    }
}