package de.neuefische.capstone.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class FinanceReport {

    private final Interval period;
    private final BigDecimal totalIncome;
    private final BigDecimal totalExpenses;
    private final BigDecimal fixCosts;
    private final BigDecimal variableCosts;
    private final BigDecimal balance;

}
