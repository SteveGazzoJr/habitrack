package com.projectdave.habitrack.service;

import com.projectdave.habitrack.exception.InvalidParameterException;
import com.projectdave.habitrack.exception.NotFoundException;
import com.projectdave.habitrack.model.ContactMethod;
import com.projectdave.habitrack.model.Role;
import com.projectdave.habitrack.model.User;
import com.projectdave.habitrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User save(User user) {
        if (isNullOrEmpty(user.getId())) {
            user.setId(UUID.randomUUID().toString());
        }
        if(isNullOrEmpty(user.getEmail()) && isNullOrEmpty(user.getPhone())) {
            throw new InvalidParameterException("User must have either phone or email");
        }
        if(isNullOrEmpty(user.getPreference())) {
            user.setPreference(ContactMethod.EMAIL);
        }
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public User get(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found for id %s".formatted(id)));
    }

    private boolean isNullOrEmpty(ContactMethod contactMethod) {
        return contactMethod == null || isNullOrEmpty(contactMethod.toString());
    }

    private boolean isNullOrEmpty(String string) {
        return string == null || string.isBlank();
    }
}
