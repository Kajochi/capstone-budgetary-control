package de.neuefische.capstone.backend.entries;

import de.neuefische.capstone.backend.model.*;
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
        List<Entry> entries = List.of(
                new Entry("1",
                        "testTitle",
                        "testDescription",
                        LocalDate.of(2023, 12, 3),
                        new BigDecimal(34),
                        Category.INCOME,
                        Interval.MONTHLY,
                        CostType.FIXED)
        );
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
        EntryWithNoId entryWithNoId = new EntryWithNoId(
                "testTitle",
                "testDescription",
                LocalDate.of(2023, 12, 3),
                new BigDecimal(34),
                Category.INCOME,
                Interval.MONTHLY,
                CostType.FIXED
        );

        String mockedID = "1";
        Entry entryExpected = new Entry(
                mockedID,
                "testTitle",
                "testDescription",
                LocalDate.of(2023, 12, 3),
                new BigDecimal(34),
                Category.INCOME,
                Interval.MONTHLY,
                CostType.FIXED);
        //When
        when(entriesRepo.insert(entryExpected)).thenReturn(entryExpected);
        when(idService.createRandomId()).thenReturn(mockedID);
        Entry actual = entriesService.addEntry(entryWithNoId);
        //Then
        verify(idService).createRandomId();
        verify(entriesRepo).insert(entryExpected);
        assertEquals(entryExpected, actual);
    }

    @Test
    void WhenEntryIsUpdatedReturnUpdatedEntry() {
        //Given
        Entry expectedEntry = new Entry(
                "1",
                "changedTitle",
                "changedDescription",
                LocalDate.of(2023, 12, 3),
                new BigDecimal(34),
                Category.INCOME,
                Interval.MONTHLY,
                CostType.FIXED
        );

        EntryWithNoId entryWithNoId = new EntryWithNoId(
                "changedTitle",
                "changedDescription",
                LocalDate.of(2023, 12, 3),
                new BigDecimal(34),
                Category.INCOME,
                Interval.MONTHLY,
                CostType.FIXED
        );

        String iD = "1";
        //When
        when(entriesRepo.save(expectedEntry)).thenReturn(expectedEntry);
        Entry actual = entriesService.updateEntry(entryWithNoId, iD);
        //Then
        verify(entriesRepo).save(expectedEntry);
        assertEquals(expectedEntry, actual);
    }

    @Test
    void WhenEntryIsDeletedReturnNothing() {
        //Given
        String iD = "1";

        //When

        when(entriesRepo.existsById(iD)).thenReturn(true);

        entriesService.deleteEntry(iD);
        //Then
        verify(entriesRepo).deleteById(iD);
    }

    @Test
    void WhenEntryIsDeletedAndDoesNotExistThrowException() {
        //Given
        String iD = "1";
        //When
        when(entriesRepo.existsById(iD)).thenReturn(false);
        //Then
        assertThrows(IllegalArgumentException.class, () -> entriesService.deleteEntry(iD));
    }
}
