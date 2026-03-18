package com.example.Capgi_BankManagementSystem.service;

import com.example.Capgi_BankManagementSystem.dto.request.AccountRequestDto;
import com.example.Capgi_BankManagementSystem.dto.response.AccountResponseDto;

public interface AccountService {
    AccountResponseDto createAccount(Integer customerId, AccountRequestDto dto);
}
