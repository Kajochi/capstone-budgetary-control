package de.neuefische.capstone.backend.monthlybalance;

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

class MonthlyBalanceServiceTest {
    MonthlySort monthlySort = mock(MonthlySort.class);
    MonthlyBalanceService monthlyBalanceService = new MonthlyBalanceService(monthlySort);
    LocalDate currentDate = LocalDate.now();
    int currentMonth = currentDate.getMonthValue();
    int currentYear = currentDate.getYear();
    int monthInOneYear = currentMonth + 12;
    @Test
    void getMonthlyBalanceList() {
        //GIVEN
        List<Entry> entries = List.of(
                new Entry("1",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(2023, 8, 22),
                        new BigDecimal(1000),
                        Category.INCOME,
                        Interval.MONTHLY,
                        CostType.FIXED));
        Map<String, MonthlyBalance> expected = new HashMap<>();
        for (int month = currentMonth; month < monthInOneYear; month++) {
            int magicMonth ;
            int year ;
            if (month <= 12) {
                magicMonth = month;
                year = currentYear;
            }else {
                magicMonth = month - 12;
                year = currentYear + 1;
            }
            String monthLabel = LocalDate.of(currentYear, magicMonth, 1).getMonth().toString().toUpperCase();
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

        //WHEN
        when(monthlySort.generateMonthlyBalanceList()).thenReturn(expected);
        Map<String, MonthlyBalance> actual = monthlyBalanceService.getMonthlyBalanceList();
        //THEN
        verify(monthlySort).generateMonthlyBalanceList();
        assertThat(actual).isEqualTo(expected);
    }
}