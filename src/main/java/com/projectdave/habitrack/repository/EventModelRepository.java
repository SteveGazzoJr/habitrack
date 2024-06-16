package com.projectdave.habitrack.repository;

import com.projectdave.habitrack.model.event.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EventModelRepository extends MongoRepository<EventModel, String> {

    @Query("{ 'title' : {$regex : /?0/i} }")
    List<EventModel> findByTitle(String title);

}
