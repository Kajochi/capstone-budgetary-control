package de.neuefische.capstone.backend.entries;

import de.neuefische.capstone.backend.model.Entry;
import de.neuefische.capstone.backend.model.EntryWithNoId;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EntriesService {

    private final EntriesRepo entriesRepo;

    private final IdService idService;

    public EntriesService(EntriesRepo entriesRepo, IdService idService) {
        this.idService = idService;
        this.entriesRepo = entriesRepo;
    }

    public List<Entry> getAllEntries() {
        return entriesRepo.findAll();
    }

    public Entry addEntry(EntryWithNoId entryWithNoId) {
        return entriesRepo.save(new Entry(
                idService.createRandomId(),
                entryWithNoId.getTitle(),
                entryWithNoId.getDescription(),
                entryWithNoId.getDate(),
                entryWithNoId.getAmount(),
                entryWithNoId.getCategory(),
                entryWithNoId.getInterval()
        ));
    }
}
