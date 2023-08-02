package de.neuefische.capstone.backend.sortentries;

import de.neuefische.capstone.backend.entries.EntriesRepo;
import de.neuefische.capstone.backend.model.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SortEntriesTest {

    EntriesRepo entriesRepo = mock(EntriesRepo.class);

    SortEntries sortEntries = new SortEntries(entriesRepo);

    @Test
    void sortedEntriesByMonth() {
        //Given
        List<Entry> entries = List.of(
                new Entry("1",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(2023, 12, 3),
                        new BigDecimal(34),
                        Category.INCOME,
                        Interval.MONTHLY,
                        CostType.FIXED),
                new Entry("2",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(2023, 12, 3),
                        new BigDecimal(34),
                        Category.INCOME,
                        Interval.MONTHLY,
                        CostType.FIXED),
                new Entry("3",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(2022, 1, 3),
                        new BigDecimal(34),
                        Category.INCOME,
                        Interval.MONTHLY,
                        CostType.FIXED),
                new Entry("4",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(2022, 1, 3),
                        new BigDecimal(34),
                        Category.INCOME,
                        Interval.MONTHLY,
                        CostType.FIXED),
                new Entry("5",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(2022, 1, 3),
                        new BigDecimal(34),
                        Category.INCOME,
                        Interval.ONCE,
                        CostType.FIXED)
        );
        List<MonthlyEntries> expectedMonthlyEntries = List.of(new MonthlyEntries(2022, 1, List.of(
                new Entry("3",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(2022, 1, 3),
                        new BigDecimal(34),
                        Category.INCOME,
                        Interval.MONTHLY,
                        CostType.FIXED),
                new Entry("4",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(2022, 1, 3),
                        new BigDecimal(34),
                        Category.INCOME,
                        Interval.MONTHLY,
                        CostType.FIXED)
        )), new MonthlyEntries(2023, 12, List.of(
                new Entry("1",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(2023, 12, 3),
                        new BigDecimal(34),
                        Category.INCOME,
                        Interval.MONTHLY,
                        CostType.FIXED),
                new Entry("2",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(2023, 12, 3),
                        new BigDecimal(34),
                        Category.INCOME,
                        Interval.MONTHLY,
                        CostType.FIXED)
        )));
        //When
        when(entriesRepo.findAll()).thenReturn(entries);
        List<MonthlyEntries> actual = sortEntries.sortEntriesByMonthAndYear();
        //Then
        assertEquals(expectedMonthlyEntries, actual);
    }

}