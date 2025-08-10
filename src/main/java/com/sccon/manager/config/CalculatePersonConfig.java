package com.sccon.manager.config;

import com.sccon.manager.adapters.out.FindPersonAdapter;
import com.sccon.manager.application.core.usecase.CalculatePersonUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public  class CalculatePersonConfig {

    @Bean
    public CalculatePersonUseCase calculatePersonUseCase(FindPersonAdapter findPersonAdapter) {
        return new CalculatePersonUseCase(findPersonAdapter);
    }
}
