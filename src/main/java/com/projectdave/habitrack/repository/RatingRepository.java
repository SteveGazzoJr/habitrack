package com.projectdave.habitrack.repository;

import com.projectdave.habitrack.model.rating.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface RatingRepository extends MongoRepository<Rating, String> {

    Optional<Rating> findByUserId(String userId);

    Optional<Rating> findByDate(LocalDate date);
}
