package de.neuefische.capstone.backend.financereport;

import de.neuefische.capstone.backend.entries.EntriesRepo;
import de.neuefische.capstone.backend.model.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.powermock.modules.junit4.PowerMockRunner;


import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
class FinanceReportCalculateTest {

    EntriesRepo entriesRepo = mock(EntriesRepo.class);


    FinanceReportCalculate financeReportCalculate = new FinanceReportCalculate(entriesRepo);
    @Test
    void WhenPeriodMonthIsGivenCalculateFinanceReports(){
        //Given
        List<Entry> filteredEntries = List.of(
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
                        CostType.FIXED)
        );
        //When
        when(entriesRepo.findAll()).thenReturn(filteredEntries);
        FinanceReport expected = new FinanceReport(
                Interval.MONTHLY,
                new BigDecimal("1000.000"),
                new BigDecimal("500.000"),
                new BigDecimal("500.000"),
                new BigDecimal(0),
                new BigDecimal("500.000")
        );
        FinanceReport actual = financeReportCalculate.calculateFinanceReports().get(0);
        //Then
        verify(entriesRepo).findAll();
        assertEquals(expected, actual);
    }
}