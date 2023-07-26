package de.neuefische.capstone.backend.model;

import org.springframework.stereotype.Component;

@Component
public class IdService {

        public String createRandomId() {
            return java.util.UUID.randomUUID().toString();
        }
}
