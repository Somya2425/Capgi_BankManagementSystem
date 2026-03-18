package com.example.Capgi_BankManagementSystem.service;

import com.example.Capgi_BankManagementSystem.dto.request.LoanRequestDto;
import com.example.Capgi_BankManagementSystem.dto.response.LoanResponseDto;

import java.util.List;

public interface LoanService {

    LoanResponseDto applyLoan(LoanRequestDto dto);

    List<LoanResponseDto> getLoansByAccount(Integer accountId);

    List<LoanResponseDto> getApprovedLoans();
}