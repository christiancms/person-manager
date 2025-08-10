package com.sccon.manager.application.core.usecase;

import com.sccon.manager.application.core.domain.Person;
import com.sccon.manager.application.core.ports.out.FindPersonOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DisplayName("Tests for CalculatePersonUseCase")
class CalculatePersonUseCaseTest {

    @Mock
    private FindPersonOutputPort findPersonOutputPort;

    @InjectMocks
    private CalculatePersonUseCase calculatePersonUseCase;

    private Person person;
    private final LocalDate currentDate = LocalDate.of(2023, 8, 10);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Cria uma pessoa mockada para ser usada nos testes
        person = new Person(
                1L, "Test User",
                LocalDate.of(1995, 8, 10), // 28 anos de idade
                LocalDate.of(2018, 8, 10) // 5 anos de empresa
        );
    }

    // --- Testes para o método calculateAge ---
    @Nested
    @DisplayName("Tests for calculateAge method")
    class CalculateAgeTests {

        @Test
        @DisplayName("should calculate age in years successfully")
        void testCalculateAgeInYears_Success() {
            // Given
            when(findPersonOutputPort.findById(1L)).thenReturn(Optional.of(person));

            // When
            Long years = calculatePersonUseCase.calculateAge(1L, "years", currentDate);

            // Then
            assertEquals(28L, years);
        }

        @Test
        @DisplayName("should calculate age in months successfully")
        void testCalculateAgeInMonths_Success() {
            // Given
            when(findPersonOutputPort.findById(1L)).thenReturn(Optional.of(person));

            // When
            Long months = calculatePersonUseCase.calculateAge(1L, "months", currentDate);

            // Then
            assertEquals(336L, months);
        }

        @Test
        @DisplayName("should throw exception when person is not found for age calculation")
        void testCalculateAge_PersonNotFound() {
            // Given
            when(findPersonOutputPort.findById(99L)).thenReturn(Optional.empty());

            // When / Then
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> calculatePersonUseCase.calculateAge(99L, "years", currentDate));
            assertEquals("Pessoa não encontrada", exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception for invalid age output format")
        void testCalculateAge_InvalidFormat() {
            // Given
            when(findPersonOutputPort.findById(1L)).thenReturn(Optional.of(person));

            // When / Then
            ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                    () -> calculatePersonUseCase.calculateAge(1L, "invalid", currentDate));
            assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
            assertEquals("Formato de saída inválido", exception.getReason());
        }
    }

    // --- Testes para o método calculateSalary ---
    @Nested
    @DisplayName("Tests for calculateSalary method")
    class CalculateSalaryTests {

        @Test
        @DisplayName("should calculate full salary successfully")
        void testCalculateFullSalary_Success() {
            // Given
            when(findPersonOutputPort.findById(1L)).thenReturn(Optional.of(person));

            // When
            BigDecimal salary = calculatePersonUseCase.calculateSalary(1L, "full", currentDate);

            // Then
            // Valor esperado após 5 anos de cálculo: 1558 * 1.18^5 + 500*5 + 500*4 + 500*3...
            BigDecimal expectedSalary = new BigDecimal("7141.44");
            assertEquals(expectedSalary, salary);
        }

        @Test
        @DisplayName("should calculate salary in minimum wage units successfully")
        void testCalculateMinSalary_Success() {
            // Given
            when(findPersonOutputPort.findById(1L)).thenReturn(Optional.of(person));

            // When
            BigDecimal minSalaryUnits = calculatePersonUseCase.calculateSalary(1L, "min", currentDate);

            // Then
            BigDecimal expectedMinSalaryUnits = new BigDecimal("5.49"); // 4482.52 / 1302.00
            assertEquals(expectedMinSalaryUnits, minSalaryUnits);
        }

        @Test
        @DisplayName("should throw exception when person is not found for salary calculation")
        void testCalculateSalary_PersonNotFound() {
            // Given
            when(findPersonOutputPort.findById(99L)).thenReturn(Optional.empty());

            // When / Then
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> calculatePersonUseCase.calculateSalary(99L, "full", currentDate));
            assertEquals("Pessoa não encontrada", exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception for invalid salary output format")
        void testCalculateSalary_InvalidFormat() {
            // Given
            when(findPersonOutputPort.findById(1L)).thenReturn(Optional.of(person));

            // When / Then
            ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                    () -> calculatePersonUseCase.calculateSalary(1L, "invalid", currentDate));
            assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
            assertEquals("Formato de saída inválido", exception.getReason());
        }
    }
}
