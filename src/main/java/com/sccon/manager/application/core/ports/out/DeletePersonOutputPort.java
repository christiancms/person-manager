package com.sccon.manager.application.core.ports.out;

/**
 * Interface @DeletePersonOutputPort contract to access Repository
 */
public interface DeletePersonOutputPort {
    void delete(Long id);
}
