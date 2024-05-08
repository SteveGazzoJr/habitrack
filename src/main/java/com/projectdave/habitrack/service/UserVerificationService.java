package com.projectdave.habitrack.service;

import com.projectdave.habitrack.config.TwilioConfig;
import com.projectdave.habitrack.exception.VerificationException;
import com.projectdave.habitrack.model.ContactMethod;
import com.projectdave.habitrack.model.User;
import com.projectdave.habitrack.model.UserVerification;
import com.projectdave.habitrack.repository.UserVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserVerificationService {

    @Autowired
    private final UserVerificationRepository userVerificationRepository;
    @Autowired
    private final UserService userService;

    @Autowired
    private final TwilioConfig twilioConfig;

    public void setVerificationCode(String userId) {
        User user = userService.getUser(userId);
        Random random = new Random();
        UserVerification userVerification = new UserVerification();
        userVerification.setSessionId(UUID.randomUUID().toString());
        userVerification.setVerificationCode(String.valueOf(random.nextInt(10000)));
        userVerification.setUserId(user.getId());
        userVerification.setExpiration(LocalDateTime.now().plusMinutes(5));
        userVerificationRepository.save(userVerification);
    }

    public void verifyCode(UserVerification input) {
        UserVerification verification = userVerificationRepository.findByUserId(input.getUserId())
                .orElseThrow(() -> new VerificationException("User not found: %s".formatted(input.getUserId())));

        if (verification.getVerificationCode() != input.getVerificationCode()) {
            throw new VerificationException("Verification code mismatch");
        }

        if(verification.getExpiration().isBefore(LocalDateTime.now())) {
            throw new VerificationException("Code expired, please request a new code to restart verification");
        }

    }

    private void sendVerificationCode(User user) {
        if (user.getPreference().equals(ContactMethod.EMAIL)) {
            //email the code
        }
        else {
            //text the code
        }
    }
}
