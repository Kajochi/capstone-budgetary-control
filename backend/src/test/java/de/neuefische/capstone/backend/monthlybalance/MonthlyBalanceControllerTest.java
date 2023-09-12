package de.neuefische.capstone.backend.monthlybalance;

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
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MonthlyBalanceControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    MonthlyBalanceService monthlyBalanceService;
    @Autowired
    ObjectMapper objectMapper;

    @DirtiesContext
    @Test
    void getMonthlyBalanceListWhenOneEntryIsAdded() throws Exception  {
        //Given
        String jsonRequestBody = objectMapper.writeValueAsString(new EntryWithNoId(
                "testTitle",
                "testDescription",
                LocalDate.of(2023, 12, 3),
                new BigDecimal(1000),
                Category.INCOME,
                Interval.MONTHLY,
                CostType.FIXED
        ));

        //When
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/entries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestBody))
                .andExpect(status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/monthlybalance"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        //Then
        assertEquals(12, monthlyBalanceService.getMonthlyBalanceList().size());

    }

}