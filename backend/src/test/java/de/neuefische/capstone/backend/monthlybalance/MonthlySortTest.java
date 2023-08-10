package de.neuefische.capstone.backend.monthlybalance;

import de.neuefische.capstone.backend.entries.EntriesRepo;
import de.neuefische.capstone.backend.model.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

class MonthlySortTest {

    EntriesRepo entriesRepo = mock(EntriesRepo.class);

    MonthlySort monthlySort = new MonthlySort(entriesRepo);

    @Test
    void whenEarliestMonthIsApril22AndLatestAugust24generateMonthlyBalanceList() {
        //Given
        Entry earliestEntry = new Entry("1",
                "testTitle",
                "testDescription",
                LocalDate.of(2023, 8, 22),
                new BigDecimal(1000),
                Category.INCOME,
                Interval.MONTHLY,
                CostType.FIXED);

        List<Entry> entries = List.of(
                new Entry("1",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(2023, 8, 22),
                        new BigDecimal(1000),
                        Category.INCOME,
                        Interval.MONTHLY,
                        CostType.FIXED));

        //When
        when(entriesRepo.findFirstByOrderByDateAsc()).thenReturn(earliestEntry);
        when(entriesRepo.findAll()).thenReturn(entries);
        Map<String, MonthlyBalance> actual = monthlySort.generateMonthlyBalanceList();
        Map<String, MonthlyBalance> expected = new HashMap<>();

        for (int month = 8; month < 21; month++) {
            int magicMonth ;
            int year ;
            if (month <= 12) {
                magicMonth = month;
                year = 2023;
            }else {
                magicMonth = month - 12;
                year = 2024;
            }
            String monthLabel = LocalDate.of(2023, magicMonth, 1).getMonth().toString().toUpperCase();
            monthLabel = monthLabel+"-"+year;
            MonthlyBalance monthlyBalance = new MonthlyBalance(monthLabel,
                    new BigDecimal("1000"),
                    new BigDecimal("0"),
                    new BigDecimal("0"),
                    new BigDecimal("0"),
                    new BigDecimal("0"),
                    new BigDecimal("1000"),
                    new ArrayList<>(entries));
            expected.put(monthLabel, monthlyBalance);
        }


        //Then
        verify(entriesRepo).findFirstByOrderByDateAsc();
        verify(entriesRepo, times(39)).findAll();
        assertThat(actual).isEqualTo(expected);
    }
}
