package com.sccon.manager.application.core.ports.out;

import com.sccon.manager.application.core.domain.Person;

import java.util.List;
import java.util.Optional;

/**
 * This interface defines the contract of the <strong>outbound port</strong>
 * that the domain layer uses to interact with the persistence layer,
 * as a repository.
 * <p>
 * In the hexagonal architecture, it isolates the business logic from the data storage
 * technology. The use case invokes the methods of this
 * interface without worrying about whether the implementation uses a relational database,
 * NoSQL, an external service, or even a mock for testing.
 * <p>
 * The concrete repository (Adapter) will implement this interface, adapting the
 * calls to the chosen persistence technology.
 * This class receives call from PersonAPI domain/core to query in database/inMemory
 */
public interface FindPersonOutputPort {

    Optional<Person> findById(Long id);
    List<Person> findAll();
}
