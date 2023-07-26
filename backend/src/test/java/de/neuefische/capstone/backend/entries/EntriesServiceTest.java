package de.neuefische.capstone.backend.entries;

import de.neuefische.capstone.backend.model.Category;
import de.neuefische.capstone.backend.model.Entry;
import de.neuefische.capstone.backend.model.EntryWithNoId;
import de.neuefische.capstone.backend.model.Interval;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EntriesServiceTest {

    EntriesRepo entriesRepo = mock(EntriesRepo.class);

    IdService idService = mock(IdService.class);

    EntriesService entriesService = new EntriesService(entriesRepo, idService);

    @Test
    void ReturnEntriesWhenListIsNotEmpty() {
        //Given
        List<Entry> entries = List.of(new Entry("1", "testTitle", "testDescription", LocalDate.of(2023, 12, 3), new BigDecimal(34), Category.INCOME, Interval.MONTHLY));
        //When
        when(entriesRepo.findAll()).thenReturn(entries);
        List<Entry> actual = entriesService.getAllEntries();
        //Then
        verify(entriesRepo).findAll();
        assertEquals(entries, actual);
    }

    @Test
    void ReturnAddedEntryWhenEntryIsAdded() {
        //Given
        EntryWithNoId entryWithNoId = new EntryWithNoId( "testTitle", "testDescription", LocalDate.of(2023, 12, 3), new BigDecimal(34), Category.INCOME, Interval.MONTHLY);
        String mockedID = "1";
        Entry entryExpected = new Entry(mockedID, "testTitle", "testDescription", LocalDate.of(2023, 12, 3), new BigDecimal(34), Category.INCOME, Interval.MONTHLY);
        //When
        when(entriesRepo.save(entryExpected)).thenReturn(entryExpected);
        when(idService.createRandomId()).thenReturn(mockedID);
        Entry actual = entriesService.addEntry(entryWithNoId);
        //Then
        verify(idService).createRandomId();
        verify(entriesRepo).save(entryExpected);
        assertEquals(entryExpected, actual);
    }
}