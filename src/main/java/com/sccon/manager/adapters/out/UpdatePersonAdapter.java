package com.sccon.manager.adapters.out;

import com.sccon.manager.application.core.domain.Person;
import com.sccon.manager.application.core.ports.out.UpdatePersonOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdatePersonAdapter implements UpdatePersonOutputPort {

    @Autowired
    private InMemoryPersonRepository personRepository;

    @Override
    public void update(Long id, Person updatePerson) {
        personRepository.update(id, updatePerson);
    }

    @Override
    public void updatePartial(Long id, Person updatePerson) {
        personRepository.updatePartial(id, updatePerson);
    }
}
