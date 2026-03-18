package com.example.Capgi_BankManagementSystem.service.impl;

import com.example.Capgi_BankManagementSystem.constants.ErrorMessages;
import com.example.Capgi_BankManagementSystem.dto.request.AccountRequestDto;
import com.example.Capgi_BankManagementSystem.dto.response.AccountResponseDto;
import com.example.Capgi_BankManagementSystem.entity.Account;
import com.example.Capgi_BankManagementSystem.entity.Customer;
import com.example.Capgi_BankManagementSystem.enums.AccountType;
import com.example.Capgi_BankManagementSystem.exception.ResourceNotFoundException;
import com.example.Capgi_BankManagementSystem.repository.AccountRepository;
import com.example.Capgi_BankManagementSystem.repository.CustomerRepository;
import com.example.Capgi_BankManagementSystem.service.AccountService;
import com.example.Capgi_BankManagementSystem.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepo;
    private final CustomerRepository customerRepo;
    private final MapperUtil mapper;

    @Override
    public AccountResponseDto createAccount(Integer customerId, AccountRequestDto dto) {

        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND));

        Account account = mapper.map(dto, Account.class);
        account.setType(AccountType.valueOf(dto.getType()));
        account.setCustomer(customer);

        return mapper.map(accountRepo.save(account), AccountResponseDto.class);
    }

    @Override
    public AccountResponseDto getAccountById(Integer id) {
        Account account = accountRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ACCOUNT_NOT_FOUND));

        return mapper.map(account, AccountResponseDto.class);
    }

    @Override
    public List<AccountResponseDto> getAccountsByCustomer(Integer customerId) {
        return accountRepo.findByCustomerId(customerId)
                .stream()
                .map(a -> mapper.map(a, AccountResponseDto.class))
                .toList();
    }

    @Override
    public void deactivateAccount(Integer id) {
        Account account = accountRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ACCOUNT_NOT_FOUND));

        account.setActive(false);
        accountRepo.save(account);
    }
}