package com.sccon.manager.application.core.usecase;

import com.sccon.manager.application.core.ports.out.DeletePersonOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Tests for DeletePersonUseCase")
class DeletePersonUseCaseTest {

    @Mock
    private DeletePersonOutputPort deletePersonOutputPort;

    @InjectMocks
    private DeletePersonUseCase deletePersonUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // --- Cenário de Sucesso (Caminho Feliz) ---
    @Test
    @DisplayName("should delete a person by ID successfully")
    void testDeletePersonById_Success() {
        // Given
        Long personId = 123L;

        // When
        deletePersonUseCase.delete(personId);

        // Then
        // Verifica se o método `delete` da porta de saída foi chamado exatamente uma vez com o ID correto
        verify(deletePersonOutputPort, times(1)).delete(personId);
    }
}
