package de.neuefische.capstone.backend.entries;

import de.neuefische.capstone.backend.model.Entry;
import de.neuefische.capstone.backend.model.EntryWithNoId;
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
        return entriesRepo.save(new Entry(
                new IdService().createRandomId(),
                entryWithNoId.getTitle(),
                entryWithNoId.getDescription(),
                entryWithNoId.getDate(),
                entryWithNoId.getAmount(),
                entryWithNoId.getCategory(),
                entryWithNoId.getInterval()
        ));
    }
}
