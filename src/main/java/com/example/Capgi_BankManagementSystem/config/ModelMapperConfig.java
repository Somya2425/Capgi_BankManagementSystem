package com.example.Capgi_BankManagementSystem.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    /**
     * ModelMapper Bean for DTO ↔ Entity conversion
     */
    @Bean
    public ModelMapper modelMapper() {

        ModelMapper mapper = new ModelMapper();

        // Strict matching → prevents wrong mapping
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true); // Prevent null overwrite (PATCH safe)

        return mapper;
    }
}