package de.neuefische.capstone.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "entries")
public class Entry {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  long id;
    @Column(name = "title")
    private  String title;
    @Column(name = "description")
    private  String description;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "category")
    private  Category category;
    @Column(name = "entry_interval")
    private  Interval interval;
    @Column(name = "costType")
    private  CostType costType;


}
