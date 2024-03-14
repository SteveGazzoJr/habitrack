package com.projectdave.habitrack.repository;

import com.projectdave.habitrack.model.EventInstance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventInstanceRepository extends MongoRepository<EventInstance, String> {

    List<EventInstance> findByUserId(String userId);
}
