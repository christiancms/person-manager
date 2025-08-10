package com.sccon.manager.application.core.usecase;

import com.sccon.manager.application.core.domain.Person;
import com.sccon.manager.application.core.ports.in.FindPersonInputPort;
import com.sccon.manager.application.core.ports.out.UpdatePersonOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Tests for UpdatePersonUseCase")
class UpdatePersonUseCaseTest {

    @Mock
    private FindPersonInputPort findPersonInputPort;

    @Mock
    private UpdatePersonOutputPort updatePersonOutputPort;

    @InjectMocks
    private UpdatePersonUseCase updatePersonUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // --- Testes para o método 'update' (atualização completa) ---
    @Test
    @DisplayName("should update a person successfully")
    void testUpdatePerson_Success() {
        // Given
        Long personId = 1L;
        Person personUpdate = new Person(null, "New Name", LocalDate.of(1990, 1, 1), LocalDate.of(2023, 1, 1));

        // When
        updatePersonUseCase.update(personId, personUpdate);

        // Then
        // Verifica se o método 'update' da porta de saída foi chamado com o ID e o objeto de atualização corretos
        verify(updatePersonOutputPort, times(1)).update(personId, personUpdate);
    }

    // --- Testes para o método 'updatePartial' (atualização parcial) ---
    @Test
    @DisplayName("should partially update a person successfully")
    void testUpdatePartialPerson_Success() {
        // Given
        Long personId = 2L;
        // Objeto com apenas o nome para simular uma atualização parcial
        Person personUpdate = new Person(null, "Partial Update Name", null, null);

        // When
        updatePersonUseCase.updatePartial(personId, personUpdate);

        // Then
        // Verifica se o método 'updatePartial' da porta de saída foi chamado com os parâmetros corretos
        verify(updatePersonOutputPort, times(1)).updatePartial(personId, personUpdate);
    }
}
