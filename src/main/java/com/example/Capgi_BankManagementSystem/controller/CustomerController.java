package com.example.Capgi_BankManagementSystem.controller;

import com.example.Capgi_BankManagementSystem.constants.ApiPaths;
import com.example.Capgi_BankManagementSystem.constants.AppConstants;
import com.example.Capgi_BankManagementSystem.constants.SuccessMessages;
import com.example.Capgi_BankManagementSystem.dto.request.CustomerRequestDto;
import com.example.Capgi_BankManagementSystem.dto.response.ApiResponse;
import com.example.Capgi_BankManagementSystem.dto.response.CustomerResponseDto;
import com.example.Capgi_BankManagementSystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.CUSTOMER)
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ApiResponse<CustomerResponseDto> create(@RequestBody CustomerRequestDto dto) {
        return ApiResponse.<CustomerResponseDto>builder()
                .status(AppConstants.STATUS_201)
                .message(SuccessMessages.CUSTOMER_CREATED)
                .data(service.createCustomer(dto))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CustomerResponseDto> getById(@PathVariable Integer id) {
        return ApiResponse.<CustomerResponseDto>builder()
                .status(AppConstants.STATUS_200)
                .message(AppConstants.MESSAGE_200)
                .data(service.getCustomerById(id))
                .build();
    }

    @GetMapping
    public ApiResponse<List<CustomerResponseDto>> getAll() {
        return ApiResponse.<List<CustomerResponseDto>>builder()
                .status(AppConstants.STATUS_200)
                .message(AppConstants.MESSAGE_200)
                .data(service.getAllCustomers())
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Integer id) {
        service.deleteCustomer(id);
        return ApiResponse.<String>builder()
                .status(AppConstants.STATUS_200)
                .message("Customer deleted successfully")
                .build();
    }
}