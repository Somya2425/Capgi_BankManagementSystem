package com.example.Capgi_BankManagementSystem.controller;

<<<<<<< HEAD
import com.example.Capgi_BankManagementSystem.constants.AppConstants;
=======
import com.example.Capgi_BankManagementSystem.constants.ApiPaths;
import com.example.Capgi_BankManagementSystem.constants.AppConstants;
import com.example.Capgi_BankManagementSystem.constants.SuccessMessages;
>>>>>>> rollback/test-version
import com.example.Capgi_BankManagementSystem.dto.request.AccountRequestDto;
import com.example.Capgi_BankManagementSystem.dto.response.AccountResponseDto;
import com.example.Capgi_BankManagementSystem.dto.response.ApiResponse;
import com.example.Capgi_BankManagementSystem.service.AccountService;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
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
=======
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.ACCOUNT)
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping("/{customerId}")
    public ApiResponse<AccountResponseDto> create(@PathVariable Integer customerId,
                                                  @RequestBody AccountRequestDto dto) {
        return ApiResponse.<AccountResponseDto>builder()
                .status(AppConstants.STATUS_201)
                .message(SuccessMessages.ACCOUNT_CREATED)
                .data(service.createAccount(customerId, dto))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<AccountResponseDto> getById(@PathVariable Integer id) {
        return ApiResponse.<AccountResponseDto>builder()
                .status(AppConstants.STATUS_200)
                .message(AppConstants.MESSAGE_200)
                .data(service.getAccountById(id))
                .build();
    }

    @GetMapping("/customer/{customerId}")
    public ApiResponse<List<AccountResponseDto>> getByCustomer(@PathVariable Integer customerId) {
        return ApiResponse.<List<AccountResponseDto>>builder()
                .status(AppConstants.STATUS_200)
                .message(AppConstants.MESSAGE_200)
                .data(service.getAccountsByCustomer(customerId))
                .build();
    }

    @PutMapping("/{id}/deactivate")
    public ApiResponse<String> deactivate(@PathVariable Integer id) {
        service.deactivateAccount(id);
        return ApiResponse.<String>builder()
                .status(AppConstants.STATUS_200)
                .message("Account deactivated successfully")
                .build();
    }
}
>>>>>>> rollback/test-version
