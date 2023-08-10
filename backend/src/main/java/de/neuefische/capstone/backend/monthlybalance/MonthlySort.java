package de.neuefische.capstone.backend.monthlybalance;

import de.neuefische.capstone.backend.entries.EntriesRepo;
import de.neuefische.capstone.backend.model.Entry;
import de.neuefische.capstone.backend.model.Interval;
import de.neuefische.capstone.backend.model.MonthlyBalance;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Data
public class MonthlySort {
    @Autowired
    private final EntriesRepo entriesRepo;

    private List<LocalDate> generateEarliestAndLatestMonthForMonthlyBalance() {
        LocalDate currentDate = LocalDate.now();
        LocalDate endDate = currentDate.plusMonths(12);

        Entry earliestEntry = entriesRepo.findFirstByOrderByDateAsc();
        LocalDate earliestTransactionDate = earliestEntry.getDate();
        LocalDate startDate = earliestTransactionDate.isBefore(currentDate) ? earliestTransactionDate : currentDate;

        return List.of(startDate, endDate);
    }

    public Map<String, MonthlyBalance> generateMonthlyBalanceList() {
        List<LocalDate> dateList = generateEarliestAndLatestMonthForMonthlyBalance();
        LocalDate startDate = dateList.get(0);
        LocalDate endDate = dateList.get(1);

        Map<String, MonthlyBalance> monthlyBalanceList = new HashMap<>();

        while (startDate.isBefore(endDate)) {
            String monthYear = startDate.getMonth().toString() + "-" + startDate.getYear();

            List<Entry> entriesSortedByStartDate = sortEntriesByStartDate(startDate);
            List<BigDecimal> entryAmounts = MonthlyBalanceCalculate.calculateEntryAmounts(entriesSortedByStartDate);

            MonthlyBalance monthlyBalance = new MonthlyBalance(monthYear,
                    entryAmounts.get(0),
                    entryAmounts.get(1),
                    entryAmounts.get(2),
                    entryAmounts.get(3),
                    entryAmounts.get(4),
                    entryAmounts.get(5),
                    entriesSortedByStartDate);

            monthlyBalanceList.put(monthYear, monthlyBalance);
            startDate = startDate.plusMonths(1);
        }
        return monthlyBalanceList;
    }

    private List<Entry> sortEntriesByStartDate(LocalDate targetDate) {
        List<Entry> monthlyEntries = entriesRepo.findAll().stream()
                .filter(entry -> entry.getInterval() == Interval.MONTHLY).toList();

        List<Entry> onceEntries = entriesRepo.findAll().stream()
                .filter(entry -> entry.getInterval() == Interval.ONCE)
                .filter(entry -> entry.getDate().getMonth() == targetDate.getMonth())
                .filter(entry -> entry.getDate().getYear() == targetDate.getYear())
                .toList();

        List<Entry> recurringEntries = entriesRepo.findAll().stream()
                .filter(entry -> entry.getInterval() == Interval.QUARTERLY
                        || entry.getInterval() == Interval.HALF_YEARLY
                        || entry.getInterval() == Interval.YEARLY)
                .filter(entry -> {
                    int multiplier = Interval.getMultiplier(entry.getInterval());
                    int monthsBetween = Math.floorMod(targetDate.getMonthValue() - 1, multiplier);
                    return monthsBetween == 0;
                })
                .toList();

        List<Entry> sortedEntries = new ArrayList<>();
        sortedEntries.addAll(monthlyEntries);
        sortedEntries.addAll(recurringEntries);
        sortedEntries.addAll(onceEntries);

        return sortedEntries;

    }
}
