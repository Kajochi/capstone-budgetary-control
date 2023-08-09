package de.neuefische.capstone.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MonthlyEntries {

    private final int year;
    private final int month;
    private final List <Entry> entries;
}
