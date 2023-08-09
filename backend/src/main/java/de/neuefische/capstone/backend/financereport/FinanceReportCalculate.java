package de.neuefische.capstone.backend.financereport;

import de.neuefische.capstone.backend.entries.EntriesRepo;
import de.neuefische.capstone.backend.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        BigDecimal totalVariableCosts = BigDecimal.ZERO;
        BigDecimal balance;


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
                    totalVariableCosts = totalVariableCosts.add(entry.getAmount().multiply(intervalNum));
                    variableExpenses = totalVariableCosts;
                }

            }
        }


        totalIncome = fixIncome.add(variableIncome);
        totalExpenses = fixExpenses.add(variableExpenses);
        balance = totalIncome.subtract(totalExpenses);


        return new FinanceReport(period, totalIncome, totalExpenses, fixCosts, totalVariableCosts, balance);

    }

    private BigDecimal calculateIntervalNum(Interval entryInterval, Interval reportPeriod) {
        int entryMultiplier = getMultiplier(entryInterval);
        int reportMultiplier = getMultiplier(reportPeriod);

        return BigDecimal.valueOf(reportMultiplier).divide(BigDecimal.valueOf(entryMultiplier), 3, RoundingMode.HALF_DOWN);

    }

}
