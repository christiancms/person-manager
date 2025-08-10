package com.sccon.manager.application.core.ports.out;

import com.sccon.manager.application.core.domain.Person;

import java.util.List;
import java.util.Optional;

public interface FindPersonOutputPort {

    Optional<Person> findById(Long id);
    List<Person> findAll();
}
