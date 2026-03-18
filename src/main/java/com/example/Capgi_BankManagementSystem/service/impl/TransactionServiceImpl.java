package com.example.Capgi_BankManagementSystem.service.impl;

import com.example.Capgi_BankManagementSystem.entity.dto.response.TransactionResponseDto;
import com.example.Capgi_BankManagementSystem.entity.Account;
import com.example.Capgi_BankManagementSystem.entity.Transaction;
import com.example.Capgi_BankManagementSystem.enums.TransactionType;
import com.example.Capgi_BankManagementSystem.exception.InsufficientBalanceException;
import com.example.Capgi_BankManagementSystem.exception.ResourceNotFoundException;
import com.example.Capgi_BankManagementSystem.repository.AccountRepository;
import com.example.Capgi_BankManagementSystem.repository.TransactionRepository;
import com.example.Capgi_BankManagementSystem.service.TransactionService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;
    private final ModelMapper modelMapper;   // ✅ Injected

    @Override
    public TransactionResponseDto deposit(Integer accountId, double amount) {

        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        account.setBalance(account.getBalance() + amount);
        accountRepo.save(account);

        Transaction txn = Transaction.builder()
                .account(account)
                .amount(amount)
                .type(TransactionType.DEPOSIT)
                .date(LocalDateTime.now())
                .build();

        transactionRepo.save(txn);

        return mapToDto(txn);
    }

    @Override
    public TransactionResponseDto withdraw(Integer accountId, double amount) {

        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepo.save(account);

        Transaction txn = Transaction.builder()
                .account(account)
                .amount(amount)
                .type(TransactionType.WITHDRAW)
                .date(LocalDateTime.now())
                .build();

        transactionRepo.save(txn);

        return mapToDto(txn);
    }

    // 🔥 ATOMIC TRANSFER
    @Override
    @Transactional
    public void transfer(Integer fromId, Integer toId, double amount) {

        Account fromAccount = accountRepo.findById(fromId)
                .orElseThrow(() -> new ResourceNotFoundException("Sender account not found"));

        Account toAccount = accountRepo.findById(toId)
                .orElseThrow(() -> new ResourceNotFoundException("Receiver account not found"));

        if (fromAccount.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepo.save(fromAccount);
        accountRepo.save(toAccount);

        Transaction debitTxn = Transaction.builder()
                .account(fromAccount)
                .amount(amount)
                .type(TransactionType.TRANSFER)
                .date(LocalDateTime.now())
                .build();

        Transaction creditTxn = Transaction.builder()
                .account(toAccount)
                .amount(amount)
                .type(TransactionType.TRANSFER)
                .date(LocalDateTime.now())
                .build();

        transactionRepo.save(debitTxn);
        transactionRepo.save(creditTxn);
    }

    @Override
    public List<TransactionResponseDto> getMiniStatement(Integer accountId) {

        accountRepo.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        return transactionRepo
                .findTop10ByAccountIdOrderByDateDesc(accountId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    // 🔁 ModelMapper Mapping
    private TransactionResponseDto mapToDto(Transaction txn) {

        TransactionResponseDto dto = modelMapper.map(txn, TransactionResponseDto.class);

        // Manual mapping for nested field
        dto.setAccountId(txn.getAccount().getId());

        return dto;
    }
}