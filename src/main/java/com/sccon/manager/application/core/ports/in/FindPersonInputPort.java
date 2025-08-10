package com.sccon.manager.application.core.ports.in;

import com.sccon.manager.application.core.domain.Person;

import java.util.List;
import java.util.Optional;

public interface FindPersonInputPort {
    Optional<Person> findById(Long id);
    List<Person> findAll();
}
