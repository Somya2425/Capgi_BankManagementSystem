package com.example.Capgi_BankManagementSystem.controller;


import com.example.Capgi_BankManagementSystem.entity.dto.response.TransactionResponseDto;
import com.example.Capgi_BankManagementSystem.service.TransactionService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    // ✅ Deposit Money
    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponseDto> deposit(
            @RequestParam Integer accountId,
            @RequestParam double amount) {

        TransactionResponseDto response =
                transactionService.deposit(accountId, amount);

        return ResponseEntity.ok(response);
    }

    // ✅ Withdraw Money
    @PostMapping("/withdraw")
    public ResponseEntity<TransactionResponseDto> withdraw(
            @RequestParam Integer accountId,
            @RequestParam double amount) {

        TransactionResponseDto response =
                transactionService.withdraw(accountId, amount);

        return ResponseEntity.ok(response);
    }

    // ✅ Transfer Money
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(
            @RequestParam Integer fromAccountId,
            @RequestParam Integer toAccountId,
            @RequestParam double amount) {

        transactionService.transfer(fromAccountId, toAccountId, amount);

        return ResponseEntity.ok("Transfer successful");
    }

    // ✅ Mini Statement (Last 10 Transactions)
    @GetMapping("/mini-statement/{accountId}")
    public ResponseEntity<List<TransactionResponseDto>> getMiniStatement(
            @PathVariable Integer accountId) {

        List<TransactionResponseDto> statement =
                transactionService.getMiniStatement(accountId);

        return ResponseEntity.ok(statement);
    }
}
