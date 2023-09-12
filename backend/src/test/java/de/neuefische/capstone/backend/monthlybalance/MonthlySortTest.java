package de.neuefische.capstone.backend.monthlybalance;

import de.neuefische.capstone.backend.entries.EntriesRepo;
import de.neuefische.capstone.backend.model.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.*;


import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

class MonthlySortTest {

    EntriesRepo entriesRepo = mock(EntriesRepo.class);

    MonthlySort monthlySort = new MonthlySort(entriesRepo);

    LocalDate currentDate = LocalDate.now();
    int currentMonth = currentDate.getMonthValue();
    int currentYear = currentDate.getYear();
    int monthInOneYear = currentMonth + 12;

   boolean calculateApplicableMonths(int interval, int startMonth, int targetMonth) {
        List <Integer> applicableMonths = new ArrayList<>();
        applicableMonths.add(startMonth);
        for(int i = 0; i <= interval; i++){
            startMonth = startMonth + interval;
            if (startMonth > 12){
                applicableMonths.add(startMonth - 12);

            }else {
                applicableMonths.add(startMonth);
            }
        }

        return applicableMonths.contains(targetMonth);

    }
    @Test
    void whenEarliestMonthIsAugust23AndLatestAugust24generateMonthlyBalanceList() {
        //Given


        Entry earliestEntry = new Entry("1",
                "testTitle",
                "testDescription",
                LocalDate.of(currentYear, currentMonth, 22),
                new BigDecimal(1000),
                Category.INCOME,
                Interval.MONTHLY,
                CostType.FIXED);

        List<Entry> entries = List.of(
                new Entry("1",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(currentYear, currentMonth, 22),
                        new BigDecimal(1000),
                        Category.INCOME,
                        Interval.MONTHLY,
                        CostType.FIXED));

        //When
        when(entriesRepo.findFirstByOrderByDateAsc()).thenReturn(earliestEntry);
        when(entriesRepo.findAll()).thenReturn(entries);
        Map<String, MonthlyBalance> actual = monthlySort.generateMonthlyBalanceList();
        Map<String, MonthlyBalance> expected = new HashMap<>();

        for (int month = currentMonth; month < monthInOneYear; month++) {
            int magicMonth;
            int year;
            if (month <= 12) {
                magicMonth = month;
                year = currentYear;
            } else {
                magicMonth = month - 12;
                year = currentYear + 1;
            }
            String monthLabel = LocalDate.of(2023, magicMonth, 1).getMonth().toString().toUpperCase();
            monthLabel = monthLabel + "-" + year;
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
        verify(entriesRepo, times(36)).findAll();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenEarliestMonthIsAugust23AndIntervalIsOnceGenerateMonthlyBalanceList() {
        //Given
        Entry earliestEntry = new Entry("1",
                "testTitle",
                "testDescription",
                LocalDate.of(currentYear, currentMonth, 22),
                new BigDecimal(1000),
                Category.INCOME,
                Interval.ONCE,
                CostType.FIXED);

        List<Entry> entries = List.of(
                new Entry("1",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(currentYear, currentMonth, 22),
                        new BigDecimal(1000),
                        Category.INCOME,
                        Interval.ONCE,
                        CostType.FIXED));

        //When
        when(entriesRepo.findFirstByOrderByDateAsc()).thenReturn(earliestEntry);
        when(entriesRepo.findAll()).thenReturn(entries);
        Map<String, MonthlyBalance> actual = monthlySort.generateMonthlyBalanceList();
        Map<String, MonthlyBalance> expected = new HashMap<>();

        for (int month = currentMonth; month < monthInOneYear; month++) {
            int magicMonth;
            int year;
            if (month <= 12) {
                magicMonth = month;
                year = currentYear;
            } else {
                magicMonth = month - 12;
                year = currentYear + 1;
            }
            if (magicMonth==currentMonth) {
                String monthLabel = LocalDate.of(2023, magicMonth, 1).getMonth().toString().toUpperCase();
                monthLabel = monthLabel + "-" + year;
                MonthlyBalance monthlyBalance = new MonthlyBalance(monthLabel,
                        new BigDecimal("1000"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("1000"),
                        new ArrayList<>(entries));
                expected.put(monthLabel, monthlyBalance);
            }else {
                String monthLabel = LocalDate.of(2023, magicMonth, 1).getMonth().toString().toUpperCase();
                monthLabel = monthLabel + "-" + year;
                MonthlyBalance monthlyBalance = new MonthlyBalance(monthLabel,
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new ArrayList<>());
                expected.put(monthLabel, monthlyBalance);
            }
        }
        verify(entriesRepo).findFirstByOrderByDateAsc();
        verify(entriesRepo, times(36)).findAll();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenEarliestIsCurrentDateAndIntervalIsQuarterlyGenerateMonthlyBalanceList(){
        //Given
        int multiplierQuarterly = 3;

        Entry earliestEntry = new Entry("1",
                "testTitle",
                "testDescription",
                LocalDate.of(currentYear, currentMonth, 22),
                new BigDecimal(1000),
                Category.INCOME,
                Interval.QUARTERLY,
                CostType.FIXED);

        List<Entry> entries = List.of(
                new Entry("1",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(currentYear, currentMonth, 22),
                        new BigDecimal(1000),
                        Category.INCOME,
                        Interval.QUARTERLY,
                        CostType.FIXED));

        //When
        when(entriesRepo.findFirstByOrderByDateAsc()).thenReturn(earliestEntry);
        when(entriesRepo.findAll()).thenReturn(entries);
        Map<String, MonthlyBalance> actual = monthlySort.generateMonthlyBalanceList();
        Map<String, MonthlyBalance> expected = new HashMap<>();

        for (int month = currentMonth; month < monthInOneYear; month++) {
            int magicMonth;
            int year;
            if (month <= 12) {
                magicMonth = month;
                year = currentYear;
            } else {
                magicMonth = month - 12;
                year = currentYear + 1;
            }



            if (magicMonth==currentMonth || calculateApplicableMonths(multiplierQuarterly, currentMonth, magicMonth)) {
                String monthLabel = LocalDate.of(2023, magicMonth, 1).getMonth().toString().toUpperCase();
                monthLabel = monthLabel + "-" + year;
                MonthlyBalance monthlyBalance = new MonthlyBalance(monthLabel,
                        new BigDecimal("1000"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("1000"),
                        new ArrayList<>(entries));
                expected.put(monthLabel, monthlyBalance);
            }else {
                String monthLabel = LocalDate.of(currentYear, magicMonth, 1).getMonth().toString().toUpperCase();
                monthLabel = monthLabel + "-" + year;
                MonthlyBalance monthlyBalance = new MonthlyBalance(monthLabel,
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new ArrayList<>());
                expected.put(monthLabel, monthlyBalance);
            }
        }
        verify(entriesRepo).findFirstByOrderByDateAsc();
        verify(entriesRepo, times(36)).findAll();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenEarliestIsCurrentDateAndIntervalIsHalfYearlyGenerateMonthlyBalanceList() {
        //Given
        int multiplierHalfYearly = 6;
        Entry earliestEntry = new Entry("1",
                "testTitle",
                "testDescription",
                LocalDate.of(currentYear, currentMonth, 22),
                new BigDecimal(1000),
                Category.INCOME,
                Interval.HALF_YEARLY,
                CostType.FIXED);

        List<Entry> entries = List.of(
                new Entry("1",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(currentYear, currentMonth, 22),
                        new BigDecimal(1000),
                        Category.INCOME,
                        Interval.HALF_YEARLY,
                        CostType.FIXED));

        //When
        when(entriesRepo.findFirstByOrderByDateAsc()).thenReturn(earliestEntry);
        when(entriesRepo.findAll()).thenReturn(entries);
        Map<String, MonthlyBalance> actual = monthlySort.generateMonthlyBalanceList();
        Map<String, MonthlyBalance> expected = new HashMap<>();

        for (int month = currentMonth; month < monthInOneYear; month++) {
            int magicMonth;
            int year;
            if (month <= 12) {
                magicMonth = month;
                year = currentYear;
            } else {
                magicMonth = month - 12;
                year = currentYear + 1;
            }
            if (calculateApplicableMonths(multiplierHalfYearly, currentMonth, magicMonth)) {
                String monthLabel = LocalDate.of(currentYear, magicMonth, 1).getMonth().toString().toUpperCase();
                monthLabel = monthLabel + "-" + year;
                MonthlyBalance monthlyBalance = new MonthlyBalance(monthLabel,
                        new BigDecimal("1000"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("1000"),
                        new ArrayList<>(entries));
                expected.put(monthLabel, monthlyBalance);
            }else {
                String monthLabel = LocalDate.of(currentYear, magicMonth, 1).getMonth().toString().toUpperCase();
                monthLabel = monthLabel + "-" + year;
                MonthlyBalance monthlyBalance = new MonthlyBalance(monthLabel,
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new ArrayList<>());
                expected.put(monthLabel, monthlyBalance);
            }
        }
        verify(entriesRepo).findFirstByOrderByDateAsc();
        verify(entriesRepo, times(36)).findAll();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenEarliestIsCurrentDateAndIntervalIsYearlyGenerateMonthlyBalanceList(){
        //Given
        int multiplierHalfYearly = 12;
        Entry earliestEntry = new Entry("1",
                "testTitle",
                "testDescription",
                LocalDate.of(currentYear, currentMonth, 22),
                new BigDecimal(1000),
                Category.INCOME,
                Interval.YEARLY,
                CostType.FIXED);

        List<Entry> entries = List.of(
                new Entry("1",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(currentYear, currentMonth, 22),
                        new BigDecimal(1000),
                        Category.INCOME,
                        Interval.YEARLY,
                        CostType.FIXED));

        //When
        when(entriesRepo.findFirstByOrderByDateAsc()).thenReturn(earliestEntry);
        when(entriesRepo.findAll()).thenReturn(entries);
        Map<String, MonthlyBalance> actual = monthlySort.generateMonthlyBalanceList();
        Map<String, MonthlyBalance> expected = new HashMap<>();

        for (int month = currentMonth; month < monthInOneYear; month++) {
            int magicMonth;
            int year;
            if (month <= 12) {
                magicMonth = month;
                year = currentYear;
            } else {
                magicMonth = month - 12;
                year = currentYear + 1;
            }
            if (calculateApplicableMonths(multiplierHalfYearly, currentMonth, magicMonth)) {
                String monthLabel = LocalDate.of(currentYear, magicMonth, 1).getMonth().toString().toUpperCase();
                monthLabel = monthLabel + "-" + year;
                MonthlyBalance monthlyBalance = new MonthlyBalance(monthLabel,
                        new BigDecimal("1000"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("1000"),
                        new ArrayList<>(entries));
                expected.put(monthLabel, monthlyBalance);
            }else {
                String monthLabel = LocalDate.of(currentYear, magicMonth, 1).getMonth().toString().toUpperCase();
                monthLabel = monthLabel + "-" + year;
                MonthlyBalance monthlyBalance = new MonthlyBalance(monthLabel,
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        new ArrayList<>());
                expected.put(monthLabel, monthlyBalance);
            }
        }
        verify(entriesRepo).findFirstByOrderByDateAsc();
        verify(entriesRepo, times(36)).findAll();
        assertThat(actual).isEqualTo(expected);
    }
}
