package de.neuefische.capstone.backend.entries;

import de.neuefische.capstone.backend.model.Entry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
