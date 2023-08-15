package de.neuefische.capstone.backend.monthlybalance;

import de.neuefische.capstone.backend.model.Category;
import de.neuefische.capstone.backend.model.CostType;
import de.neuefische.capstone.backend.model.Entry;
import de.neuefische.capstone.backend.model.Interval;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyBalanceCalculateTest {


    @Test
    void calculateEntryAmountsWhenEntriesAreGiven() {
        //Given
        List<Entry> entries = List.of(
                new Entry("1",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(2023, 12, 3),
                        new BigDecimal(1000),
                        Category.INCOME,
                        Interval.MONTHLY,
                        CostType.FIXED),
                new Entry("2",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(2023, 12, 3),
                        new BigDecimal(500),
                        Category.EXPENSE,
                        Interval.MONTHLY,
                        CostType.FIXED),
                new Entry("3",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(2023, 12, 3),
                        new BigDecimal(200),
                        Category.EXPENSE,
                        Interval.MONTHLY,
                        CostType.VARIABLE),
                new Entry("4",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(2023, 12, 3),
                        new BigDecimal(100),
                        Category.EXPENSE,
                        Interval.ONCE,
                        CostType.VARIABLE)
        );
        List<BigDecimal> expected = List.of(
                new BigDecimal("1000"),
                new BigDecimal("800"),
                new BigDecimal("500"),
                new BigDecimal("300"),
                new BigDecimal("100"),
                new BigDecimal("200"));
        //When
        List<BigDecimal> actual = MonthlyBalanceCalculate.calculateEntryAmounts(entries);
        //Then
        assertEquals(expected, actual);
    }
}