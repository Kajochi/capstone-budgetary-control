package de.neuefische.capstone.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EntriesSortByInterval {

    private final Interval interval;

    List<Entry> entries;
}
