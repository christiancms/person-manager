package com.sccon.manager.config;

import com.sccon.manager.adapters.out.UpdatePersonAdapter;
import com.sccon.manager.application.core.usecase.FindPersonUseCase;
import com.sccon.manager.application.core.usecase.UpdatePersonUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdatePersonConfig {

    @Bean
    public UpdatePersonUseCase updatePersonUseCase(FindPersonUseCase findPersonUseCase,
            UpdatePersonAdapter updatePersonAdapter){
        return new UpdatePersonUseCase(findPersonUseCase, updatePersonAdapter);
    }
}
