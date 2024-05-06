package com.projectdave.habitrack.service;

import com.projectdave.habitrack.config.TwilioConfig;
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

    @Autowired
    private final TwilioConfig twilioConfig;

    public UserService(UserRepository userRepository, TwilioConfig twilioConfig) {
        this.userRepository = userRepository;
        this.twilioConfig = twilioConfig;
    }

    //TODO twilio 2fa send/verify


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
