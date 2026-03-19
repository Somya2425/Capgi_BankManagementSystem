package com.example.Capgi_BankManagementSystem.exception;

import com.example.Capgi_BankManagementSystem.constants.AppConstants;
import com.example.Capgi_BankManagementSystem.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ================= NOT FOUND =================
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        return buildError(ex.getMessage(), AppConstants.STATUS_404, request.getRequestURI());
    }

    // ================= BAD REQUEST =================
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(BadRequestException ex, HttpServletRequest request) {
        return buildError(ex.getMessage(), AppConstants.STATUS_400, request.getRequestURI());
    }

    // ================= BUSINESS =================
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBusiness(BusinessException ex, HttpServletRequest request) {
        return buildError(ex.getMessage(), AppConstants.STATUS_400, request.getRequestURI());
    }

    // ================= INSUFFICIENT BALANCE =================
    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInsufficientBalance(InsufficientBalanceException ex, HttpServletRequest request) {
        return buildError(ex.getMessage(), AppConstants.STATUS_400, request.getRequestURI());
    }

    // ================= VALIDATION (@Valid) =================
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return buildError(message, AppConstants.STATUS_400, request.getRequestURI());
    }

    // ================= CONSTRAINT =================
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraint(ConstraintViolationException ex, HttpServletRequest request) {
        return buildError(ex.getMessage(), AppConstants.STATUS_400, request.getRequestURI());
    }

    // ================= GLOBAL =================
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGlobal(Exception ex, HttpServletRequest request) {
        return buildError(ex.getMessage(), AppConstants.STATUS_500, request.getRequestURI());
    }

    // ================= COMMON METHOD =================
    private ErrorResponse buildError(String message, String status, String path) {
        return ErrorResponse.builder()
                .apiPath(path)
                .status(status)
                .errorMessage(message)
                .errorTime(LocalDateTime.now())
                .build();
    }
}