package com.example.Capgi_BankManagementSystem.util;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    private final ModelMapper modelMapper;

    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> T map(Object source, Class<T> target) {
        return modelMapper.map(source, target);
    }
}