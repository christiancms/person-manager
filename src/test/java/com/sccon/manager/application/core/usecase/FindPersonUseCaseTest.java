package com.sccon.manager.application.core.usecase;

import com.sccon.manager.application.core.domain.Person;
import com.sccon.manager.application.core.ports.out.FindPersonOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Tests for FindPersonUseCase")
class FindPersonUseCaseTest {

    @Mock
    private FindPersonOutputPort findPersonOutputPort;

    @InjectMocks
    private FindPersonUseCase findPersonUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // --- Testes para o método findById ---

    @Test
    @DisplayName("should find a person by ID successfully")
    void testFindById_Success() {
        // Given
        Long personId = 1L;
        Person mockPerson = new Person(personId, "Huguinho", LocalDate.of(2005, 5, 15), LocalDate.of(2020, 1, 10));

        // When
        // Configura o mock para retornar um Optional com a pessoa quando findById for chamado com o ID
        when(findPersonOutputPort.findById(personId)).thenReturn(Optional.of(mockPerson));
        Optional<Person> result = findPersonUseCase.findById(personId);

        // Then
        // Verifica se o resultado é não-vazio, se o mock foi chamado e se a pessoa retornada é a esperada
        assertTrue(result.isPresent());
        assertEquals(personId, result.get().getId());
        verify(findPersonOutputPort, times(1)).findById(personId);
    }

    @Test
    @DisplayName("should return an empty Optional when person ID is not found")
    void testFindById_NotFound() {
        // Given
        Long personId = 99L;

        // When
        // Configura o mock para retornar um Optional vazio quando findById for chamado com um ID não existente
        when(findPersonOutputPort.findById(personId)).thenReturn(Optional.empty());
        Optional<Person> result = findPersonUseCase.findById(personId);

        // Then
        // Verifica se o resultado é vazio e se o mock foi chamado
        assertFalse(result.isPresent());
        verify(findPersonOutputPort, times(1)).findById(personId);
    }

    // --- Testes para o método findAll ---

    @Test
    @DisplayName("should find all people successfully")
    void testFindAll_Success() {
        // Given
        List<Person> mockPersonList = List.of(
                new Person(1L, "Huguinho", LocalDate.of(2005, 5, 15), LocalDate.of(2020, 1, 10)),
                new Person(2L, "Zézinho", LocalDate.of(1995, 3, 22), LocalDate.of(2018, 6, 5))
        );

        // When
        // Configura o mock para retornar uma lista de pessoas quando findAll for chamado
        when(findPersonOutputPort.findAll()).thenReturn(mockPersonList);
        List<Person> result = findPersonUseCase.findAll();

        // Then
        // Verifica se a lista não está vazia, se tem o tamanho correto e se o mock foi chamado
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals("Huguinho", result.get(0).getName());
        verify(findPersonOutputPort, times(1)).findAll();
    }

    @Test
    @DisplayName("should return an empty list when no people are found")
    void testFindAll_EmptyList() {
        // Given
        List<Person> emptyList = Collections.emptyList();

        // When
        // Configura o mock para retornar uma lista vazia
        when(findPersonOutputPort.findAll()).thenReturn(emptyList);
        List<Person> result = findPersonUseCase.findAll();

        // Then
        // Verifica se a lista retornada é vazia e se o mock foi chamado
        assertTrue(result.isEmpty());
        verify(findPersonOutputPort, times(1)).findAll();
    }
}
