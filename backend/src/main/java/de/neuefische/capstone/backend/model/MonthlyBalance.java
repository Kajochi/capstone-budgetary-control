package de.neuefische.capstone.backend.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MonthlyBalance {

    private final String month;
    private final BigDecimal totalIncome;
    private final BigDecimal totalExpenses;
    private final BigDecimal fixedCosts;
    private final BigDecimal variableCosts;
    private final BigDecimal oneTimeCosts;
    private final BigDecimal balance;

    private final List<Entry> monthlyEntries;
}
