package de.neuefische.capstone.backend.financereport;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.capstone.backend.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
 class FinanceReportIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    FinanceReportService financeReportService;
    @Autowired
    ObjectMapper objectMapper;

    @DirtiesContext
    @Test
    void WhenEntriesAreAddedReturnListOfFinanceReports() throws Exception {
        //Given
        String jsonRequestBodyFirstEntry = objectMapper.writeValueAsString(new EntryWithNoId(
                "testTitle",
                "testDescription",
                LocalDate.of(2023, 12, 3),
                new BigDecimal(1000),
                Category.INCOME,
                Interval.MONTHLY,
                CostType.FIXED
        ));
        String jsonRequestBodySecondEntry = objectMapper.writeValueAsString(new EntryWithNoId(
                "testTitle",
                "testDescription",
                LocalDate.of(2023, 12, 3),
                new BigDecimal(500),
                Category.EXPENSE,
                Interval.MONTHLY,
                CostType.FIXED
        ));
        String jsonRequestBodyThirdEntry = objectMapper.writeValueAsString(new EntryWithNoId(
                "testTitle",
                "testDescription",
                LocalDate.of(2023, 12, 3),
                new BigDecimal(200),
                Category.EXPENSE,
                Interval.MONTHLY,
                CostType.VARIABLE
        ));
        //When
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/entries")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequestBodyFirstEntry)
                )
                .andExpect(status().isOk());
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/entries")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequestBodySecondEntry)
                )
                                .andExpect(status().isOk());
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/entries")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequestBodyThirdEntry)
                )
                .andExpect(status().isOk());

        String responseString = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/financeReports")
                )

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

             List<FinanceReport> actual = objectMapper.readValue(responseString, new com.fasterxml.jackson.core.type.TypeReference<>() {
             });
             List<FinanceReport> expected = List.of(
                     new FinanceReport(
                             Interval.MONTHLY,
                             new BigDecimal("1000.000"),
                             new BigDecimal("700.000"),
                             new BigDecimal("500.000"),
                             new BigDecimal("200.000"),
                             new BigDecimal("300.000")
                     ),
                     new FinanceReport(
                             Interval.QUARTERLY,
                             new BigDecimal("3000.000"),
                             new BigDecimal("2100.000"),
                             new BigDecimal("1500.000"),
                             new BigDecimal("600.000"),
                             new BigDecimal("900.000")
                     ),
                     new FinanceReport(
                             Interval.HALF_YEARLY,
                             new BigDecimal("6000.000"),
                             new BigDecimal("4200.000"),
                             new BigDecimal("3000.000"),
                             new BigDecimal("1200.000"),
                             new BigDecimal("1800.000")
                     ),
                     new FinanceReport(
                             Interval.YEARLY,
                             new BigDecimal("12000.000"),
                             new BigDecimal("8400.000"),
                             new BigDecimal("6000.000"),
                             new BigDecimal("2400.000"),
                             new BigDecimal("3600.000")
             ));
             assertEquals(expected, actual);
    }

    }

