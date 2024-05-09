package com.projectdave.habitrack.service;

import com.projectdave.habitrack.config.TwilioConfig;
import com.projectdave.habitrack.exception.VerificationException;
import com.projectdave.habitrack.model.ContactMethod;
import com.projectdave.habitrack.model.User;
import com.projectdave.habitrack.model.UserVerification;
import com.projectdave.habitrack.repository.UserVerificationRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Component
public class UserVerificationService {

    @Autowired
    private final UserVerificationRepository userVerificationRepository;
    @Autowired
    private final UserService userService;

    @Autowired
    private final TwilioConfig twilioConfig;



    public UserVerificationService(UserVerificationRepository userVerificationRepository, UserService userService, TwilioConfig twilioConfig) {
        this.userVerificationRepository = userVerificationRepository;
        this.userService = userService;
        this.twilioConfig = twilioConfig;
        Twilio.init(twilioConfig.getAccount(), twilioConfig.getToken());
    }

    public void sendVerificationCode(String userId) {
        User user = userService.getUser(userId);
        Random random = new Random();
        UserVerification userVerification = new UserVerification();
        userVerification.setSessionId(UUID.randomUUID().toString());
        userVerification.setVerificationCode(String.valueOf(random.nextInt(10000)));
        userVerification.setUserId(user.getId());
        userVerification.setExpiration(LocalDateTime.now().plusMinutes(5));
        userVerificationRepository.save(userVerification);
        sendVerificationCode(user);
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
            Message.creator(
                    new com.twilio.type.PhoneNumber("+14128745155"),
                    new com.twilio.type.PhoneNumber(twilioConfig.getNumber()),
                    userVerificationRepository.findByUserId(user.getId())
                            .orElseThrow(() -> new VerificationException("Unable to find verification code"))
                            .getVerificationCode())
                    .create();
        }
    }
}
