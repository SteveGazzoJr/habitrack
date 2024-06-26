package com.projectdave.habitrack.service;

import com.projectdave.habitrack.config.TwilioConfig;
import com.projectdave.habitrack.exception.VerificationException;
import com.projectdave.habitrack.model.login.CodeRequest;
import com.projectdave.habitrack.model.login.InitiateVerificationRequest;
import com.projectdave.habitrack.model.login.UserVerificationEntity;
import com.projectdave.habitrack.model.user.ContactMethod;
import com.projectdave.habitrack.model.user.User;
import com.projectdave.habitrack.repository.UserVerificationRepository;
import com.projectdave.habitrack.util.JwtUtil;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Component
@Slf4j
public class UserVerificationService {

    @Autowired
    private final UserVerificationRepository userVerificationRepository;
    @Autowired
    private final UserService userService;
    @Autowired
    private final TwilioConfig twilioConfig;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtUtil jwtUtil;



    public UserVerificationService(UserVerificationRepository userVerificationRepository, UserService userService, TwilioConfig twilioConfig, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userVerificationRepository = userVerificationRepository;
        this.userService = userService;
        this.twilioConfig = twilioConfig;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        Twilio.init(twilioConfig.getAccount(), twilioConfig.getToken());
    }

    public User sendVerificationCode(InitiateVerificationRequest request) {
        User user = userService.getUserByEmail(request.getEmail());
        UserVerificationEntity existingEntity = userVerificationRepository.findByUserId(user.getId()).orElseGet(() -> null);
        if (existingEntity != null){
            if(existingEntity.getExpiration().isAfter(LocalDateTime.now())) {
                return user;
            }
            else {
                userVerificationRepository.delete(existingEntity);
            }
        }

        //TODO add env flag to disable random codes and instead set code to predetermined value
        //so I don't end up signing my house over to Twilio
        Random random = new Random();
        UserVerificationEntity userVerificationEntity = new UserVerificationEntity();
        userVerificationEntity.setSessionId(UUID.randomUUID().toString());
        userVerificationEntity.setVerificationCode(twilioConfig.isTestEnv() ? "1234" : String.format("%04d", random.nextInt(10000)));
        userVerificationEntity.setUserId(user.getId());
        userVerificationEntity.setExpiration(LocalDateTime.now().plusMinutes(5));
        userVerificationRepository.save(userVerificationEntity);
        sendVerificationCode(user, request.getPreference());
        return user;
    }

    public String verifyCode(CodeRequest input) {
        User user = userService.getUser(input.getUserId());
        UserVerificationEntity existingEntity = userVerificationRepository.findByUserId(user.getId())
                .orElseThrow(() -> new VerificationException("Verification code not found for user: %s".formatted(user.getId())));

        if(existingEntity.getExpiration().isBefore(LocalDateTime.now())) {
            userVerificationRepository.delete(existingEntity);
            throw new VerificationException("Code expired, please request a new code to restart verification");
        }

        if (!existingEntity.getVerificationCode().equalsIgnoreCase(input.getCode())) {
            throw new VerificationException("Verification code mismatch");
        }
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getUserId(), input.getCode()));
        String token = jwtUtil.createToken(user);
        userVerificationRepository.delete(existingEntity);
        return token;
    }

    private void sendVerificationCode(User user, ContactMethod contactMethod) {
        if (twilioConfig.isTestEnv()) return;
        String message = "Your code is %s. It will expire at %s";
        UserVerificationEntity userVerificationEntity = userVerificationRepository.findByUserId(user.getId())
                .orElseThrow(() -> new VerificationException("Unable to find verification code"));
        if (contactMethod.equals(ContactMethod.EMAIL)) {
            //email the code
        }
        else {
            Message.creator(
                    new com.twilio.type.PhoneNumber("+14128745155"),
                    new com.twilio.type.PhoneNumber(twilioConfig.getNumber()),
                    message.formatted(userVerificationEntity.getVerificationCode(), userVerificationEntity.getExpiration()))
                    .create();
        }
    }
}
