package com.sccon.manager.adapters.out.repository;

import com.sccon.manager.application.core.domain.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    List<Person> findAll();
    Optional<Person> findById(Long id);
    void save(Person person);
    void delete(Long id);
    void update(Long id, Person personUpdate);
    void updatePartial(Long id, Person personUpdate);
    public Boolean isIdAvailable(Long id);
}
