package com.projectdave.habitrack.repository;

import com.projectdave.habitrack.model.UserVerification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserVerificationRepository extends MongoRepository<UserVerification, String> {
    Optional<UserVerification> findByUserId(String userId);
}
