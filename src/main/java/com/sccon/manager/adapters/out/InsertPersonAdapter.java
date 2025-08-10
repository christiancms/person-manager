package com.sccon.manager.adapters.out;

import com.sccon.manager.application.core.domain.Person;
import com.sccon.manager.application.core.ports.out.InsertPersonOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is used to persiste data repository/inMemory
 */
@Component
public class InsertPersonAdapter implements InsertPersonOutputPort {

    @Autowired
    private InMemoryPersonRepository personRepository;

    @Override
    public void insert(Person person) {
        personRepository.save(person);
    }

    @Override
    public Boolean isIdAvailable(Long id) {
        return personRepository.isIdAvailable(id);
    }
}
