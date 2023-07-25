package de.neuefische.capstone.backend.entries;

import de.neuefische.capstone.backend.model.Entry;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EntriesService {

    EntriesRepo entriesRepo;

    public EntriesService(EntriesRepo entriesRepo) {
        this.entriesRepo = entriesRepo;
    }

    public List<Entry> getAllEntries() {
        return entriesRepo.findAll();
    }
}
