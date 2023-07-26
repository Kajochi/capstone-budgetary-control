package de.neuefische.capstone.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Document("entries")
public class Entry {
    @Id
    private final String id;

    private final String title;

    private final String description;

    private final LocalDate date;

    private final BigDecimal amount;

    private final Category category;

    private final Interval interval;
}
