package com.projectdave.habitrack.repository;

import com.projectdave.habitrack.model.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventInstanceRepository extends MongoRepository<EventModel, String> {
}
