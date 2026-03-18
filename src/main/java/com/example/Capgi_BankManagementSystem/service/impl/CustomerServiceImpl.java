package com.example.Capgi_BankManagementSystem.service.impl;

import com.example.Capgi_BankManagementSystem.constants.ErrorMessages;
import com.example.Capgi_BankManagementSystem.dto.request.CustomerRequestDto;
import com.example.Capgi_BankManagementSystem.dto.response.CustomerResponseDto;
import com.example.Capgi_BankManagementSystem.entity.Customer;
import com.example.Capgi_BankManagementSystem.exception.ResourceNotFoundException;
import com.example.Capgi_BankManagementSystem.repository.CustomerRepository;
import com.example.Capgi_BankManagementSystem.service.CustomerService;
import com.example.Capgi_BankManagementSystem.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final MapperUtil mapper;

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto dto) {
        Customer customer = mapper.map(dto, Customer.class);
        return mapper.map(repository.save(customer), CustomerResponseDto.class);
    }

    @Override
    public CustomerResponseDto getCustomerById(Integer id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND));

        return mapper.map(customer, CustomerResponseDto.class);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        return repository.findAll()
                .stream()
                .map(c -> mapper.map(c, CustomerResponseDto.class))
                .toList();
    }

    @Override
    public void deleteCustomer(Integer id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND));

        repository.delete(customer);
    }
}