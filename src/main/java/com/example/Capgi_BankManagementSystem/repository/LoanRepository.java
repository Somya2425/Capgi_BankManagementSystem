package com.example.Capgi_BankManagementSystem.repository;

import com.example.Capgi_BankManagementSystem.entity.Loan;
import com.example.Capgi_BankManagementSystem.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

    // Get loans by account
    List<Loan> findByAccountId(Integer accountId);

    // Get approved loans
    List<Loan> findByStatus(LoanStatus status);

    // Count active loans
    long countByAccountIdAndStatus(Integer accountId, LoanStatus status);
}