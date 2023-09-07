package de.neuefische.capstone.backend.entries;

import de.neuefische.capstone.backend.model.Entry;
import de.neuefische.capstone.backend.model.EntryWithNoId;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EntriesService {

    private final EntriesRepo entriesRepo;



    public EntriesService(EntriesRepo entriesRepo) {

        this.entriesRepo = entriesRepo;
    }

    public List<Entry> getAllEntries() {
        return entriesRepo.findAll();
    }

    public Entry addEntry(EntryWithNoId entryWithNoId) {
        Entry entry = new Entry();
        entry.setTitle(entryWithNoId.getTitle());
        entry.setDescription(entryWithNoId.getDescription());
        entry.setDate(entryWithNoId.getDate());
        entry.setAmount(entryWithNoId.getAmount());
        entry.setCategory(entryWithNoId.getCategory());
        entry.setInterval(entryWithNoId.getInterval());
        entry.setCostType(entryWithNoId.getCostType());
        return entriesRepo.save(entry);
    }

    public Entry updateEntry(EntryWithNoId entryWithNoId, Long id ) {
        Entry existingEntry = entriesRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entry with id " + id + " not found"));

        existingEntry.setTitle(entryWithNoId.getTitle());
        existingEntry.setDescription(entryWithNoId.getDescription());
        existingEntry.setDate(entryWithNoId.getDate());
        existingEntry.setAmount(entryWithNoId.getAmount());
        existingEntry.setCategory(entryWithNoId.getCategory());
        existingEntry.setInterval(entryWithNoId.getInterval());
        existingEntry.setCostType(entryWithNoId.getCostType());

        return entriesRepo.save(existingEntry);
    }

    public void deleteEntry(long id) {
        if (!entriesRepo.existsById(id)) throw new IllegalArgumentException();
        entriesRepo.deleteById(id);
    }
}
