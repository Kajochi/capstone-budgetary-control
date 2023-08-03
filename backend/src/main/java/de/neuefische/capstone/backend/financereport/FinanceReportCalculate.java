package de.neuefische.capstone.backend.financereport;

import de.neuefische.capstone.backend.entries.EntriesRepo;
import de.neuefische.capstone.backend.model.*;
import de.neuefische.capstone.backend.sortentries.SortEntries;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static de.neuefische.capstone.backend.model.Interval.getMultiplier;

@Data
@AllArgsConstructor
@Component
public class FinanceReportCalculate {

    private final EntriesRepo entriesRepo;


    public List<FinanceReport> calculateFinanceReports() {
        List<Entry> filteredEntries = new ArrayList<>();
        entriesRepo.findAll().stream().filter(entry -> !entry.getInterval().equals(Interval.ONCE)).forEach(filteredEntries::add);

        List<FinanceReport> financeReports = new ArrayList<>();

        financeReports.add(calculateAverageForPeriod(filteredEntries, Interval.MONTHLY));
        financeReports.add(calculateAverageForPeriod(filteredEntries, Interval.QUARTERLY));
        financeReports.add(calculateAverageForPeriod(filteredEntries, Interval.HALF_YEARLY));
        financeReports.add(calculateAverageForPeriod(filteredEntries, Interval.YEARLY));

        return financeReports;
    }


    private FinanceReport calculateAverageForPeriod(List<Entry> filteredEntries, Interval period) {


        BigDecimal fixIncome = BigDecimal.ZERO;
        BigDecimal variableIncome = BigDecimal.ZERO;
        BigDecimal totalIncome;
        BigDecimal fixExpenses = BigDecimal.ZERO;
        BigDecimal variableExpenses = BigDecimal.ZERO;
        BigDecimal totalExpenses;
        BigDecimal fixCosts = BigDecimal.ZERO;
        BigDecimal variableCosts = BigDecimal.ZERO;
        BigDecimal totalVariableCosts;
        BigDecimal balance;

        int numMonthsConsidered = 1;


        for (Entry entry : filteredEntries) {

            BigDecimal intervalNum = calculateIntervalNum(entry.getInterval(), period);

            if (entry.getCostType().equals(CostType.FIXED)) {
                if (entry.getCategory().equals(Category.INCOME)) {
                    fixIncome = fixIncome.add(entry.getAmount().multiply(intervalNum));
                } else {
                    fixCosts = fixCosts.add(entry.getAmount().multiply(intervalNum));
                    fixExpenses = fixCosts;
                }
            } else {
                if (entry.getCategory().equals(Category.INCOME)) {
                    variableIncome = variableIncome.add(entry.getAmount().multiply(intervalNum));
                } else {
                    variableCosts = variableCosts.add(entry.getAmount().multiply(intervalNum));
                    variableExpenses = variableCosts;
                }
                numMonthsConsidered++;
            }
        }


        totalIncome = fixIncome.add(variableIncome.divide(BigDecimal.valueOf(numMonthsConsidered), 2, BigDecimal.ROUND_HALF_DOWN));
        totalExpenses = fixExpenses.add(variableExpenses.divide(BigDecimal.valueOf(numMonthsConsidered), 2, BigDecimal.ROUND_HALF_DOWN));
        totalVariableCosts = variableCosts.divide(BigDecimal.valueOf(numMonthsConsidered), 2, BigDecimal.ROUND_HALF_DOWN);
        balance = totalIncome.subtract(totalExpenses);


        return new FinanceReport(Period.MONTH, totalIncome, totalExpenses, fixCosts, totalVariableCosts, balance);

    }

    private BigDecimal calculateIntervalNum(Interval entryInterval, Interval reportPeriod) {
        int entryMultiplier = getMultiplier(entryInterval);
        int reportMultiplier = getMultiplier(reportPeriod);

        return BigDecimal.valueOf(reportMultiplier).divide(BigDecimal.valueOf(entryMultiplier), 8, BigDecimal.ROUND_HALF_DOWN);

    }

}