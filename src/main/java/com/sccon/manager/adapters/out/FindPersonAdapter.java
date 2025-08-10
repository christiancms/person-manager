package com.sccon.manager.adapters.out;

import com.sccon.manager.application.core.domain.Person;
import com.sccon.manager.application.core.ports.out.FindPersonOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindPersonAdapter implements FindPersonOutputPort {

    @Autowired
    private InMemoryPersonRepository personRepository;

    @Override
    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
