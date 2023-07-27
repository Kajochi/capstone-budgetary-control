package de.neuefische.capstone.backend.entries;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EntriesIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    EntriesService entriesService;

    @DirtiesContext
    @Test
    void WhenListIsEmptyReturnEmptyList() throws Exception {
        //When
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/entries")


                )
                //Then
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty())
                .andExpect(status().isOk());
    }

    @DirtiesContext
    @Test
    void WhenEntryIsAddedReturnAddedEntry() throws Exception {
        //When
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/entries")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"title\": \"testTitle\",\n" +
                                        "    \"description\": \"testDescription\",\n" +
                                        "    \"date\": \"2023-12-03\",\n" +
                                        "    \"amount\": 34,\n" +
                                        "    \"category\": \"INCOME\",\n" +
                                        "    \"interval\": \"MONTHLY\"\n" +
                                        "}")
                )
                //Then
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("testTitle"))
                .andExpect(jsonPath("$.description").value("testDescription"))
                .andExpect(jsonPath("$.date").value("2023-12-03"))
                .andExpect(jsonPath("$.amount").value(34))
                .andExpect(jsonPath("$.category").value("INCOME"))
                .andExpect(jsonPath("$.interval").value("MONTHLY"))
                .andExpect(status().isOk());
    }

    @DirtiesContext
    @Test
    void WhenEntryIsUpdatedReturnUpdatedEntry() throws Exception {
        //Given
        String id = "1";
        //When
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/entries/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"title\": \"changedTitle\",\n" +
                                        "    \"description\": \"changedDescription\",\n" +
                                        "    \"date\": \"2023-12-03\",\n" +
                                        "    \"amount\": 34,\n" +
                                        "    \"category\": \"INCOME\",\n" +
                                        "    \"interval\": \"MONTHLY\"\n" +
                                        "}")
                )
                //Then
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("changedTitle"))
                .andExpect(jsonPath("$.description").value("changedDescription"))
                .andExpect(jsonPath("$.date").value("2023-12-03"))
                .andExpect(jsonPath("$.amount").value(34))
                .andExpect(jsonPath("$.category").value("INCOME"))
                .andExpect(jsonPath("$.interval").value("MONTHLY"))
                .andExpect(status().isOk());
    }

}
