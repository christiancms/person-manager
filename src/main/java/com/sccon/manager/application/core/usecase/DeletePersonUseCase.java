package com.sccon.manager.application.core.usecase;

import com.sccon.manager.application.core.ports.in.DeletePersonInputPort;
import com.sccon.manager.application.core.ports.out.DeletePersonOutputPort;

public class DeletePersonUseCase implements DeletePersonInputPort {

    private final DeletePersonOutputPort deletePersonOutputPort;

    public DeletePersonUseCase(DeletePersonOutputPort deletePersonOutputPort) {
        this.deletePersonOutputPort = deletePersonOutputPort;
    }

    @Override
    public void delete(Long id) {
        deletePersonOutputPort.delete(id);
    }
}
