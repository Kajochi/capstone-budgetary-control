package de.neuefische.capstone.backend.entries;

import de.neuefische.capstone.backend.model.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface EntriesRepo extends MongoRepository<Entry, String> {


    Entry findFirstByOrderByDateAsc();
}
