package com.example.Capgi_BankManagementSystem.exception;

/**
 * Thrown when account balance is insufficient for withdrawal/transfer
 */
public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(String message) {
        super(message);
    }
}