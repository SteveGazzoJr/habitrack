package com.projectdave.habitrack.service;

import com.projectdave.habitrack.exception.InvalidParameterException;
import com.projectdave.habitrack.exception.NotFoundException;
import com.projectdave.habitrack.model.ContactMethod;
import com.projectdave.habitrack.model.Role;
import com.projectdave.habitrack.model.User;
import com.projectdave.habitrack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public User save(User user) {
        validateUser(user);
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    //TODO better email and phone validation
    private void validateUser(User user) {
        if (isNullOrEmpty(user.getId())) {
            user.setId(UUID.randomUUID().toString());
        }
        if(isNullOrEmpty(user.getDisplayName())) {
            throw new InvalidParameterException("User must have display name");
        }
        if(isNullOrEmpty(user.getEmail()) && isNullOrEmpty(user.getPhone())) {
            throw new InvalidParameterException("User must have either phone or email");
        }
        if(isNullOrEmpty(user.getPreference())) {
            user.setPreference(ContactMethod.EMAIL);
        }
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new InvalidParameterException("Email address is already registered: %s".formatted(user.getEmail()));
        }
        if(userRepository.existsByPhone(user.getPhone())) {
            throw new InvalidParameterException("Phone number is already registered: %s".formatted(user.getPhone()));
        }
    }

    public User getUser(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found for id %s".formatted(id)));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found for email %s".formatted(email)));
    }

    private boolean isNullOrEmpty(ContactMethod contactMethod) {
        return contactMethod == null || isNullOrEmpty(contactMethod.toString());
    }

    private boolean isNullOrEmpty(String string) {
        return string == null || string.isBlank();
    }
}
