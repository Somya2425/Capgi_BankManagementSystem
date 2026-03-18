package com.example.Capgi_BankManagementSystem.dto.response;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String apiPath;
    private HttpStatus status;
    private String errorMessage;
    private LocalDateTime errorTime;
}