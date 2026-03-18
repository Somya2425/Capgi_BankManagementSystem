package com.example.Capgi_BankManagementSystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Standard error response for all exceptions
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String apiPath;        // API endpoint
    private String status;         // HTTP status code
    private String errorMessage;   // Error message
    private LocalDateTime errorTime; // Timestamp
}