package com.example.Capgi_BankManagementSystem.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    private final ModelMapper modelMapper;

    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Convert Source → Destination
     */
    public <S, D> D map(S source, Class<D> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }

    /**
     * Update existing object (PATCH safe)
     */
    public <S, D> void map(S source, D destination) {
        modelMapper.map(source, destination);
    }
}