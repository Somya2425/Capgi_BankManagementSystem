package com.example.Capgi_BankManagementSystem.service.impl;

import com.example.Capgi_BankManagementSystem.constants.ErrorMessages;
import com.example.Capgi_BankManagementSystem.dto.request.LoanRequestDto;
import com.example.Capgi_BankManagementSystem.dto.response.LoanResponseDto;
import com.example.Capgi_BankManagementSystem.entity.Account;
import com.example.Capgi_BankManagementSystem.entity.Loan;
import com.example.Capgi_BankManagementSystem.enums.LoanStatus;
import com.example.Capgi_BankManagementSystem.exception.BusinessException;
import com.example.Capgi_BankManagementSystem.exception.ResourceNotFoundException;
import com.example.Capgi_BankManagementSystem.repository.AccountRepository;
import com.example.Capgi_BankManagementSystem.repository.LoanRepository;
import com.example.Capgi_BankManagementSystem.service.LoanService;
import com.example.Capgi_BankManagementSystem.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepo;
    private final AccountRepository accountRepo;
    private final MapperUtil mapper;

    @Override
    public LoanResponseDto applyLoan(LoanRequestDto dto) {

        Account account = accountRepo.findById(dto.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ACCOUNT_NOT_FOUND));

        long activeLoans = loanRepo.countByAccountIdAndStatus(dto.getAccountId(), LoanStatus.APPROVED);

        if (activeLoans >= 3) {
            throw new BusinessException(ErrorMessages.MAX_LOAN_LIMIT);
        }

        if (dto.getAmount() > account.getBalance() * 5) {
            throw new BusinessException(ErrorMessages.LOAN_LIMIT_EXCEEDED);
        }

        Loan loan = new Loan();
        loan.setAccount(account);
        loan.setAmount(dto.getAmount());
        loan.setStatus(LoanStatus.APPROVED);

        return mapper.map(loanRepo.save(loan), LoanResponseDto.class);
    }

    @Override
    public List<LoanResponseDto> getLoansByAccount(Integer accountId) {
        return loanRepo.findByAccountId(accountId)
                .stream()
                .map(l -> mapper.map(l, LoanResponseDto.class))
                .toList();
    }

    @Override
    public List<LoanResponseDto> getApprovedLoans() {
        return loanRepo.findByStatus(LoanStatus.APPROVED)
                .stream()
                .map(l -> mapper.map(l, LoanResponseDto.class))
                .toList();
    }
}