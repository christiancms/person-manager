package com.sccon.manager.application.core.ports.out;

import com.sccon.manager.application.core.domain.Person;

public interface InsertPersonOutputPort {
    void insert(Person person);
    public Boolean isIdAvailable(Long id);
}
