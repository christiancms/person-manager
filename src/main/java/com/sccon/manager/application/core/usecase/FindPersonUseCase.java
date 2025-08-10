package com.sccon.manager.application.core.usecase;

import com.sccon.manager.application.core.domain.Person;
import com.sccon.manager.application.core.ports.in.FindPersonInputPort;
import com.sccon.manager.application.core.ports.out.FindPersonOutputPort;

import java.util.List;
import java.util.Optional;

public class FindPersonUseCase implements FindPersonInputPort {

    private final FindPersonOutputPort findPersonOutputPort;

    public FindPersonUseCase(FindPersonOutputPort findPersonOutputPort) {
        this.findPersonOutputPort = findPersonOutputPort;
    }

    @Override
    public Optional<Person> findById(Long id) {
        return findPersonOutputPort.findById(id);
    }

    @Override
    public List<Person> findAll() {
        return findPersonOutputPort.findAll();
    }
}
