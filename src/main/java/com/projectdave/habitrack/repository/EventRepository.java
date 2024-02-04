package com.projectdave.habitrack.repository;

import com.projectdave.habitrack.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
}
