package com.projectdave.habitrack.repository;

import com.projectdave.habitrack.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
