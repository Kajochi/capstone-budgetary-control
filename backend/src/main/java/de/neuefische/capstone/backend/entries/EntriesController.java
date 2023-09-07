package de.neuefische.capstone.backend.entries;

import de.neuefische.capstone.backend.model.Entry;
import de.neuefische.capstone.backend.model.EntryWithNoId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entries")
public class EntriesController {

    private final EntriesService entriesService;

    EntriesController(EntriesService entriesService) {
        this.entriesService = entriesService;
    }
    @GetMapping
    List<Entry> getEntries() {
        return entriesService.getAllEntries();
    }

    @PostMapping
    Entry addEntry(@RequestBody EntryWithNoId entryWithNoId) {
        return entriesService.addEntry(entryWithNoId);
    }

    @PutMapping("{id}")
    Entry updateEntry(@RequestBody EntryWithNoId entryWithNoId, @PathVariable Long id) {
        return entriesService.updateEntry(entryWithNoId, id);
    }

    @DeleteMapping("{id}")
    void deleteEntry(@PathVariable Long id) {
        entriesService.deleteEntry(id);
    }
}
