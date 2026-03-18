package com.example.Capgi_BankManagementSystem.controller;

import com.example.Capgi_BankManagementSystem.constants.AppConstants;
import com.example.Capgi_BankManagementSystem.dto.request.AccountRequestDto;
import com.example.Capgi_BankManagementSystem.dto.response.AccountResponseDto;
import com.example.Capgi_BankManagementSystem.dto.response.ApiResponse;
import com.example.Capgi_BankManagementSystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService service;

    @PostMapping("/{customerId}")
    public ResponseEntity<ApiResponse<AccountResponseDto>> createAccount(
            @PathVariable Integer customerId,@RequestBody AccountRequestDto dto) {

        AccountResponseDto response = service.createAccount(customerId, dto);

        return ResponseEntity.ok(
                new ApiResponse<>(AppConstants.STATUS_201, AppConstants.MESSAGE_201, response)
        );
    }
}
