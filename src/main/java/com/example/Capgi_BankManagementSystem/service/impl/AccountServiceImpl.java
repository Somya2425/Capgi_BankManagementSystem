package com.example.Capgi_BankManagementSystem.service.impl;

import com.example.Capgi_BankManagementSystem.dto.request.AccountRequestDto;
import com.example.Capgi_BankManagementSystem.dto.response.AccountResponseDto;
import com.example.Capgi_BankManagementSystem.repository.AccountRepository;
import com.example.Capgi_BankManagementSystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;


    @Override
    public AccountResponseDto createAccount(Integer customerId, AccountRequestDto dto) {
        return null;
    }
}
