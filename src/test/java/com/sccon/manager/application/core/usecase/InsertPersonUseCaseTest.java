package com.sccon.manager.application.core.usecase;

import com.sccon.manager.application.core.domain.Person;
import com.sccon.manager.application.core.ports.out.InsertPersonOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Tests for InsertPersonUseCase")
class InsertPersonUseCaseTest {

    @Mock
    private InsertPersonOutputPort insertPersonOutputPort;

    @InjectMocks
    private InsertPersonUseCase insertPersonUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // --- Cenário de Sucesso (Caminho Feliz) ---
    @Test
    @DisplayName("should insert a person successfully")
    void testInsertPersonSuccess() {
        // Given
        Person person = new Person(null, "John Doe", null, null);

        // When
        insertPersonUseCase.insert(person);

        // Then
        // Verifica se o método `insert` da porta de saída foi chamado exatamente uma vez com o objeto person correto
        verify(insertPersonOutputPort, times(1)).insert(person);
    }

    // --- Cenário de Validação ---
    @Test
    @DisplayName("should return true when the ID is available")
    void testIsIdAvailable_shouldReturnTrue() {
        // Given
        Long id = 123L;
        // Configura o mock para retornar TRUE quando o método `isIdAvailable` for chamado com o ID
        when(insertPersonOutputPort.isIdAvailable(id)).thenReturn(true);

        // When
        Boolean result = insertPersonUseCase.isIdAvailable(id);

        // Then
        // Verifica se o resultado é TRUE e se o método do mock foi chamado
        assertTrue(result);
        verify(insertPersonOutputPort, times(1)).isIdAvailable(id);
    }

    // --- Cenário de Erro (ID já existe) ---
    @Test
    @DisplayName("should return false when the ID is not available")
    void testIsIdAvailable_shouldReturnFalse() {
        // Given
        Long id = 456L;
        // Configura o mock para retornar FALSE quando o método `isIdAvailable` for chamado com o ID
        when(insertPersonOutputPort.isIdAvailable(id)).thenReturn(false);

        // When
        Boolean result = insertPersonUseCase.isIdAvailable(id);

        // Then
        // Verifica se o resultado é FALSE e se o método do mock foi chamado
        assertFalse(result);
        verify(insertPersonOutputPort, times(1)).isIdAvailable(id);
    }
}
