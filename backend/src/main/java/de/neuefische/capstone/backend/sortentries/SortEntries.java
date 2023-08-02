package de.neuefische.capstone.backend.sortentries;

import de.neuefische.capstone.backend.entries.EntriesRepo;
import de.neuefische.capstone.backend.model.Entry;
import de.neuefische.capstone.backend.model.Interval;
import de.neuefische.capstone.backend.model.MonthlyEntries;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class SortEntries {

    List<MonthlyEntries> sortedEntriesByMonth;

    EntriesRepo entriesRepo;


    public List<MonthlyEntries> sortedEntriesByMonth(){

        List<Entry> filteredEntries = new ArrayList<>();
        entriesRepo.findAll().stream().filter(entry -> !entry.getInterval().equals(Interval.ONCE)).forEach(filteredEntries::add);

        Map<YearMonth, List<Entry>> entriesByYearMonth = filteredEntries.stream()
                .collect(Collectors.groupingBy(entry -> YearMonth.from(entry.getDate())));

        List<MonthlyEntries> monthlyEntries = new ArrayList<>();
        for (Map.Entry<YearMonth, List<Entry>> entry : entriesByYearMonth.entrySet()) {
            YearMonth yearMonth = entry.getKey();
            List<Entry> entriesForMonth = entry.getValue();
            monthlyEntries.add(new MonthlyEntries(yearMonth.getYear(), yearMonth.getMonthValue(), entriesForMonth));
        }

        return monthlyEntries;

    }





}
