package com.sccon.manager.config;

import com.sccon.manager.adapters.out.FindPersonAdapter;
import com.sccon.manager.application.core.usecase.FindPersonUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindPersonConfig {

    @Bean
    public FindPersonUseCase findPersonUseCase(FindPersonAdapter findPersonAdapter) {
        return new FindPersonUseCase(findPersonAdapter);
    }
}
