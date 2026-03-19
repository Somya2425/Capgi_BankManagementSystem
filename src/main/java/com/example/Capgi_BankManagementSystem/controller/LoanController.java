package com.example.Capgi_BankManagementSystem.controller;

import com.example.Capgi_BankManagementSystem.constants.ApiPaths;
import com.example.Capgi_BankManagementSystem.constants.AppConstants;
import com.example.Capgi_BankManagementSystem.constants.SuccessMessages;
import com.example.Capgi_BankManagementSystem.dto.request.LoanRequestDto;
import com.example.Capgi_BankManagementSystem.dto.response.ApiResponse;
import com.example.Capgi_BankManagementSystem.dto.response.LoanResponseDto;
import com.example.Capgi_BankManagementSystem.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.LOAN)
@RequiredArgsConstructor
public class LoanController {

    private final LoanService service;

    @PostMapping
    public ApiResponse<LoanResponseDto> apply(@RequestBody LoanRequestDto dto) {
        return ApiResponse.<LoanResponseDto>builder()
                .status(AppConstants.STATUS_201)
                .message(SuccessMessages.LOAN_APPLIED)
                .data(service.applyLoan(dto))
                .build();
    }

    @GetMapping("/account/{accountId}")
    public ApiResponse<List<LoanResponseDto>> getByAccount(@PathVariable Integer accountId) {
        return ApiResponse.<List<LoanResponseDto>>builder()
                .status(AppConstants.STATUS_200)
                .message(AppConstants.MESSAGE_200)
                .data(service.getLoansByAccount(accountId))
                .build();
    }

    @GetMapping("/approved")
    public ApiResponse<List<LoanResponseDto>> getApproved() {
        return ApiResponse.<List<LoanResponseDto>>builder()
                .status(AppConstants.STATUS_200)
                .message(AppConstants.MESSAGE_200)
                .data(service.getApprovedLoans())
                .build();
    }
}