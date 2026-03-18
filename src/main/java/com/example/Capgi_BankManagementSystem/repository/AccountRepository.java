package com.example.Capgi_BankManagementSystem.repository;

import com.example.Capgi_BankManagementSystem.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

}
