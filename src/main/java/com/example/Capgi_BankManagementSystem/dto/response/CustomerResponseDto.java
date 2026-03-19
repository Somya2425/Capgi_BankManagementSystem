package com.example.Capgi_BankManagementSystem.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CustomerResponseDto {

    private Integer id;
    private String name;
    private String email;

    // Optional: show account IDs or details
    private List<AccountResponseDto> accounts;
}