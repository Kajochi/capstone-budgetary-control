package de.neuefische.capstone.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EntryWithNoId {

    private final String title;
    private final String description;
    private final LocalDate date;
    private final BigDecimal amount;
    private final Category category;
    private final Interval interval;
}
