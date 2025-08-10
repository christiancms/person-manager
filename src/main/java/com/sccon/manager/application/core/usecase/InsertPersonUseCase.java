package com.sccon.manager.application.core.usecase;

import com.sccon.manager.application.core.domain.Person;
import com.sccon.manager.application.core.ports.in.InsertPersonInputPort;
import com.sccon.manager.application.core.ports.out.InsertPersonOutputPort;

/**
 * InsertPersonUseCase - business rules to save person - services
 */
public class InsertPersonUseCase implements InsertPersonInputPort {

    private final InsertPersonOutputPort insertPersonOutputPort;

    public InsertPersonUseCase(InsertPersonOutputPort insertPersonOutputPort) {
        this.insertPersonOutputPort = insertPersonOutputPort;
    }

    @Override
    public void insert(Person person) {
        insertPersonOutputPort.insert(person);
    }

    @Override
    public Boolean isIdAvailable(Long id) {
        return insertPersonOutputPort.isIdAvailable(id);
    }
}
