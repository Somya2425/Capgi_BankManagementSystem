package com.example.Capgi_BankManagementSystem.service;

import com.example.Capgi_BankManagementSystem.dto.request.AccountRequestDto;
import com.example.Capgi_BankManagementSystem.dto.response.AccountResponseDto;

<<<<<<< HEAD
public interface AccountService {
    AccountResponseDto createAccount(Integer customerId, AccountRequestDto dto);
}
=======
import java.util.List;

public interface AccountService {

    AccountResponseDto createAccount(Integer customerId, AccountRequestDto dto);

    AccountResponseDto getAccountById(Integer id);

    List<AccountResponseDto> getAccountsByCustomer(Integer customerId);

    void deactivateAccount(Integer id);
}
>>>>>>> rollback/test-version
