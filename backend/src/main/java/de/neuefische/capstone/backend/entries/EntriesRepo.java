package de.neuefische.capstone.backend.entries;

import de.neuefische.capstone.backend.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;



@Repository
public interface EntriesRepo extends JpaRepository<Entry, Long> {


    Entry findFirstByOrderByDateAsc();
}
