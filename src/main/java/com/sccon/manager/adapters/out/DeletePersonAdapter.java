package com.sccon.manager.adapters.out;

import com.sccon.manager.application.core.ports.out.DeletePersonOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * DeletePersonAdapter - implements output port to delete data repository/inMemory
 */
@Component
public class DeletePersonAdapter implements DeletePersonOutputPort {

    @Autowired
    private InMemoryPersonRepository personRepository;

    @Override
    public void delete(Long id) {
        personRepository.delete(id);
    }
}
