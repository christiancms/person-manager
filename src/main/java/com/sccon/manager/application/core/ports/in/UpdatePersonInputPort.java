package com.sccon.manager.application.core.ports.in;

import com.sccon.manager.application.core.domain.Person;

/**
 * This class is used inside service layer - Example (UpdatePersonUseCase)
 */
public interface UpdatePersonInputPort {
    void update(Long id, Person person);
    void updatePartial(Long id, Person person);
}
