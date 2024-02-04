package com.projectdave.habitrack.repository;

import com.projectdave.habitrack.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
