package com.sccon.manager.application.core.ports.in;

import com.sccon.manager.application.core.domain.Person;

import java.util.List;
import java.util.Optional;

/**
 * This interface defines the contract for the use case that handles a specific
 * request.
 * <p>
 * In the hexagonal architecture, this interface is the <strong>inbound port</strong>
 * to the domain layer. It establishes the methods that the application layer
 * (usually the endpoint controller) should invoke to interact with the
 * business logic.
 * <p>
 * The goal is to decouple the use case from implementation details, such as
 * how the request is received (via HTTP, command line, etc.).
 *
 * Contract for FindPersonUseCase.
 */
public interface FindPersonInputPort {
    Optional<Person> findById(Long id);
    List<Person> findAll();
}
