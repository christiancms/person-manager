package com.sccon.manager.config;

import com.sccon.manager.adapters.out.DeletePersonAdapter;
import com.sccon.manager.application.core.usecase.DeletePersonUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeletePersonConfig {

    @Bean
    public DeletePersonUseCase deletePersonUseCase(DeletePersonAdapter deletePersonAdapter){
        return new DeletePersonUseCase(deletePersonAdapter);
    }
}
