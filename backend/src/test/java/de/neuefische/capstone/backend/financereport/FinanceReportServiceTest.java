package de.neuefische.capstone.backend.financereport;

import de.neuefische.capstone.backend.model.FinanceReport;
import de.neuefische.capstone.backend.model.Interval;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FinanceReportServiceTest {

    FinanceReportCalculate financeReportCalculate = mock(FinanceReportCalculate.class);
    FinanceReportService financeReportService = new FinanceReportService(financeReportCalculate);

    @Test
    void getFinanceReports() {
        //GIVEN
        List <FinanceReport> givenFinanceReports = List.of(
                new FinanceReport(
                        Interval.MONTHLY,
                        new BigDecimal("1000.000"),
                        new BigDecimal("500.000"),
                        new BigDecimal("500.000"),
                        new BigDecimal(0),
                        new BigDecimal("500.000")
                ),
                new FinanceReport(
                        Interval.QUARTERLY,
                        new BigDecimal("1000.000"),
                        new BigDecimal("500.000"),
                        new BigDecimal("500.000"),
                        new BigDecimal(0),
                        new BigDecimal("500.000")
                ),
                new FinanceReport(
                        Interval.HALF_YEARLY,
                        new BigDecimal("1000.000"),
                        new BigDecimal("500.000"),
                        new BigDecimal("500.000"),
                        new BigDecimal(0),
                        new BigDecimal("500.000")
                ),
                new FinanceReport(
                        Interval.YEARLY,
                        new BigDecimal("1000.000"),
                        new BigDecimal("500.000"),
                        new BigDecimal("500.000"),
                        new BigDecimal(0),
                        new BigDecimal("500.000")
                )
        );
        //WHEN
        when(financeReportCalculate.calculateFinanceReports()).thenReturn(givenFinanceReports);
        List<FinanceReport> expected = List.of(
                new FinanceReport(
                        Interval.MONTHLY,
                        new BigDecimal("1000.000"),
                        new BigDecimal("500.000"),
                        new BigDecimal("500.000"),
                        new BigDecimal(0),
                        new BigDecimal("500.000")
                ),
                new FinanceReport(
                        Interval.QUARTERLY,
                        new BigDecimal("1000.000"),
                        new BigDecimal("500.000"),
                        new BigDecimal("500.000"),
                        new BigDecimal(0),
                        new BigDecimal("500.000")
                ),
                new FinanceReport(
                        Interval.HALF_YEARLY,
                        new BigDecimal("1000.000"),
                        new BigDecimal("500.000"),
                        new BigDecimal("500.000"),
                        new BigDecimal(0),
                        new BigDecimal("500.000")
                ),
                new FinanceReport(
                        Interval.YEARLY,
                        new BigDecimal("1000.000"),
                        new BigDecimal("500.000"),
                        new BigDecimal("500.000"),
                        new BigDecimal(0),
                        new BigDecimal("500.000")
                )
        );
        List<FinanceReport> actual = financeReportService.getFinanceReports();

        //THEN
        assertEquals(expected, actual);
    }

}