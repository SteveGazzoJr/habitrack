package com.projectdave.habitrack.repository;

import com.projectdave.habitrack.model.EventInstance;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<EventInstance, String> {
}