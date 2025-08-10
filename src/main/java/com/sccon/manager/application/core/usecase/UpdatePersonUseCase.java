package com.sccon.manager.application.core.usecase;

import com.sccon.manager.application.core.domain.Person;
import com.sccon.manager.application.core.ports.in.FindPersonInputPort;
import com.sccon.manager.application.core.ports.in.UpdatePersonInputPort;
import com.sccon.manager.application.core.ports.out.UpdatePersonOutputPort;

/**
 * UpdatePersonUseCase - business rules to update person - services
 */
public class UpdatePersonUseCase implements UpdatePersonInputPort {

    private final FindPersonInputPort findPersonInputPort;
    private final UpdatePersonOutputPort updatePersonOutputPort;

    public UpdatePersonUseCase(FindPersonInputPort findPersonInputPort, UpdatePersonOutputPort updatePersonOutputPort) {
        this.findPersonInputPort = findPersonInputPort;
        this.updatePersonOutputPort = updatePersonOutputPort;
    }

    @Override
    public void update(Long id, Person updatePerson) {
        updatePersonOutputPort.update(id, updatePerson);
    }

    @Override
    public void updatePartial(Long id, Person updatePerson) {
        updatePersonOutputPort.updatePartial(id, updatePerson);
    }
}
