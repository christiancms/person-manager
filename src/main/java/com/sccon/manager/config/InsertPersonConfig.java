package com.sccon.manager.config;

import com.sccon.manager.adapters.out.InsertPersonAdapter;
import com.sccon.manager.application.core.usecase.InsertPersonUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertPersonConfig {

    @Bean
    public InsertPersonUseCase insertPersonUseCase(InsertPersonAdapter insertPersonAdapter) {
        return new InsertPersonUseCase(insertPersonAdapter);
    }
}
