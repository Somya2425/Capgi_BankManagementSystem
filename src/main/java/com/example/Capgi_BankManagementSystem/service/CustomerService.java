package com.example.Capgi_BankManagementSystem.service;

import com.example.Capgi_BankManagementSystem.dto.request.CustomerRequestDto;
import com.example.Capgi_BankManagementSystem.dto.response.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    CustomerResponseDto createCustomer(CustomerRequestDto dto);

    CustomerResponseDto getCustomerById(Integer id);

    List<CustomerResponseDto> getAllCustomers();

    void deleteCustomer(Integer id);
}