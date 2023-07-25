package de.neuefische.capstone.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Document("entries")
public class Entry {
    @Id
    private String id;

    private String title;

    private String description;

    private LocalDate date;

    private BigDecimal amount;

    private Category category;

    private Interval interval;
}
