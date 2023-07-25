package de.neuefische.capstone.backend.entries;

import de.neuefische.capstone.backend.model.Category;
import de.neuefische.capstone.backend.model.Entry;
import de.neuefische.capstone.backend.model.Interval;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EntriesServiceTest {

    EntriesRepo entriesRepo = mock(EntriesRepo.class);

    EntriesService entriesService = new EntriesService(entriesRepo);
    @Test
    void ReturnEntriesWhenListIsNotEmpty(){
        //Given
        List<Entry> entries = List.of(new Entry("1","testTitle", "testDescription", LocalDate.of(2023,12,3), new BigDecimal(34), Category.INCOME, Interval.MONTHLY));
        //When
        when(entriesRepo.findAll()).thenReturn(entries);
        List<Entry> actual = entriesService.getAllEntries();
        //Then
        verify(entriesRepo).findAll();
        assertEquals(entries, actual);
    }
}