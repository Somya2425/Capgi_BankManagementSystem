package com.example.Capgi_BankManagementSystem.repository;

import com.example.Capgi_BankManagementSystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // Optional (for unique email validation)
    boolean existsByEmail(String email);
}