package com.sccon.manager.application.core.usecase;

import com.sccon.manager.application.core.domain.Person;
import com.sccon.manager.application.core.ports.in.CalculatePersonInputPort;
import com.sccon.manager.application.core.ports.out.FindPersonOutputPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalculatePersonUseCase implements CalculatePersonInputPort {

    private static final BigDecimal MINIMUM_SALARY = new BigDecimal("1302.00");
    private final FindPersonOutputPort findPersonOutputPort;

    public CalculatePersonUseCase(FindPersonOutputPort findPersonOutputPort) {
        this.findPersonOutputPort = findPersonOutputPort;
    }

    @Override
    public Long calculateAge(Long id, String outputFormat, LocalDate currentDate) {
        Person person = findPersonOutputPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada"));
        LocalDate birthDate = person.getBirth();
        return switch (outputFormat.toLowerCase()) {
            case "days" -> ChronoUnit.DAYS.between(birthDate, currentDate);
            case "months" -> ChronoUnit.MONTHS.between(birthDate, currentDate);
            case "years" -> ChronoUnit.YEARS.between(birthDate, currentDate);
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de saída inválido");
        };
    }

    @Override
    public BigDecimal calculateSalary(Long id, String outputFormat, LocalDate currentDate) {
        Person person = findPersonOutputPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada"));
        long years = ChronoUnit.YEARS.between(person.getAdmission(), currentDate);
        BigDecimal salary = new BigDecimal("1558.00");
        for (long i = 0; i < years; i++) {
            salary = salary.multiply(new BigDecimal("1.18")).add(new BigDecimal("500"));
        }
        salary = salary.setScale(2, RoundingMode.UP);
        return switch (outputFormat.toLowerCase()) {
            case "full" -> salary;
            case "min" -> salary.divide(MINIMUM_SALARY, 2, RoundingMode.UP);
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de saída inválido");
        };
    }
}
