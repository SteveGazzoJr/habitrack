package com.projectdave.habitrack.repository;

import com.projectdave.habitrack.model.login.UserVerificationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserVerificationRepository extends MongoRepository<UserVerificationEntity, String> {
    Optional<UserVerificationEntity> findByUserId(String userId);
}
