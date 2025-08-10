package com.sccon.manager.application.core.ports.in;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CalculatePersonInputPort {

    Long calculateAge(Long id, String outputFormat, LocalDate currentDate);
    BigDecimal calculateSalary(Long id, String outputFormat, LocalDate currentDate);
}
