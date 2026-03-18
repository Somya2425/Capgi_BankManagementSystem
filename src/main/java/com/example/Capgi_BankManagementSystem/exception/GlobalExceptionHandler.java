package com.example.Capgi_BankManagementSystem.exception;

import com.example.Capgi_BankManagementSystem.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponse handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        return ErrorResponse.builder()
                .apiPath(request.getRequestURI())
                .status(HttpStatus.NOT_FOUND)
                .errorMessage(ex.getMessage())
                .errorTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ErrorResponse handleBalance(InsufficientBalanceException ex, HttpServletRequest request) {
        return ErrorResponse.builder()
                .apiPath(request.getRequestURI())
                .status(HttpStatus.BAD_REQUEST)
                .errorMessage(ex.getMessage())
                .errorTime(LocalDateTime.now())
                .build();
    }
}