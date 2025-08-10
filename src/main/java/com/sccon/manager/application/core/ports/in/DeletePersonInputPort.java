package com.sccon.manager.application.core.ports.in;

/**
 * This class is used inside service layer - Example (DeletePersonUseCase)
 */
public interface DeletePersonInputPort {
    void delete(Long id);
}
