package com.sccon.manager.application.core.ports.in;

import com.sccon.manager.application.core.domain.Person;

/**
 * This class is used inside service layer - Example (InsertPersonUseCase)
 */
public interface InsertPersonInputPort {
    void insert(Person person);
    Boolean isIdAvailable(Long id);
}
