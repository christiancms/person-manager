package com.sccon.manager.application.core.ports.out;

import com.sccon.manager.application.core.domain.Person;

public interface UpdatePersonOutputPort {
    void update(Long id, Person person);
    void updatePartial(Long id, Person person);
}
