package com.example.Capgi_BankManagementSystem.service.impl;

import com.example.Capgi_BankManagementSystem.constants.ErrorMessages;
import com.example.Capgi_BankManagementSystem.dto.response.TransactionResponseDto;
import com.example.Capgi_BankManagementSystem.entity.Account;
import com.example.Capgi_BankManagementSystem.entity.Transaction;
import com.example.Capgi_BankManagementSystem.enums.TransactionType;
import com.example.Capgi_BankManagementSystem.exception.BadRequestException;
import com.example.Capgi_BankManagementSystem.exception.BusinessException;
import com.example.Capgi_BankManagementSystem.exception.InsufficientBalanceException;
import com.example.Capgi_BankManagementSystem.exception.ResourceNotFoundException;
import com.example.Capgi_BankManagementSystem.repository.AccountRepository;
import com.example.Capgi_BankManagementSystem.repository.TransactionRepository;
import com.example.Capgi_BankManagementSystem.service.TransactionService;
import com.example.Capgi_BankManagementSystem.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepo;
    private final TransactionRepository txnRepo;
    private final MapperUtil mapper;

    // ================= DEPOSIT =================
    @Override
    public void deposit(Integer accountId, Double amount) {

        validateAmount(amount);

        Account account = getActiveAccount(accountId);

        account.setBalance(account.getBalance() + amount);
        accountRepo.save(account);

        saveTxn(account, amount, TransactionType.DEPOSIT);
    }

    // ================= WITHDRAW =================
    @Override
    public void withdraw(Integer accountId, Double amount) {

        validateAmount(amount);

        Account account = getActiveAccount(accountId);

        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException(ErrorMessages.INSUFFICIENT_BALANCE);
        }

        account.setBalance(account.getBalance() - amount);
        accountRepo.save(account);

        saveTxn(account, amount, TransactionType.WITHDRAW);
    }

    // ================= TRANSFER =================
    @Override
    @Transactional
    public void transfer(Integer fromId, Integer toId, Double amount) {

        validateAmount(amount);

        Account from = getActiveAccount(fromId);
        Account to = getActiveAccount(toId);

        if (from.getBalance() < amount) {
            throw new InsufficientBalanceException(ErrorMessages.INSUFFICIENT_BALANCE);
        }

        // debit
        from.setBalance(from.getBalance() - amount);

        // credit
        to.setBalance(to.getBalance() + amount);

        accountRepo.save(from);
        accountRepo.save(to);

        saveTxn(from, amount, TransactionType.TRANSFER);
        saveTxn(to, amount, TransactionType.TRANSFER);
    }

    // ================= GET TRANSACTIONS =================
    @Override
    public List<TransactionResponseDto> getTransactions(Integer accountId) {

        return txnRepo.findByAccountId(accountId)
                .stream()
                .map(t -> mapper.map(t, TransactionResponseDto.class))
                .toList();
    }

    // ================= HELPER METHODS =================

    private Account getAccount(Integer id) {
        return accountRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(ErrorMessages.ACCOUNT_NOT_FOUND));
    }

    private Account getActiveAccount(Integer id) {
        Account account = getAccount(id);

        if (!account.isActive()) {
            throw new BusinessException(ErrorMessages.ACCOUNT_INACTIVE);
        }

        return account;
    }

    private void validateAmount(Double amount) {
        if (amount == null || amount <= 0) {
            throw new BadRequestException("Amount must be greater than 0");
        }
    }

    private void saveTxn(Account account, Double amount, TransactionType type) {
        Transaction txn = new Transaction();
        txn.setAccount(account);
        txn.setAmount(amount);
        txn.setType(type);
        txn.setDate(LocalDateTime.now());
        txnRepo.save(txn);
    }
}