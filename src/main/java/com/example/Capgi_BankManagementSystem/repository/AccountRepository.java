package com.example.Capgi_BankManagementSystem.repository;

import com.example.Capgi_BankManagementSystem.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

}
=======

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    // Get all accounts by customer
    List<Account> findByCustomerId(Integer customerId);
}
>>>>>>> rollback/test-version
