package com.projectdave.habitrack.controller;

import com.projectdave.habitrack.model.UserVerification;
import com.projectdave.habitrack.service.UserVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VerificationController {

    @Autowired
    private final UserVerificationService userVerificationService;

    @PostMapping("/verificationCode/send")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<Void> setVerificationCode(@RequestBody String user) {
        userVerificationService.sendVerificationCode(user);
        return (ResponseEntity<Void>) ResponseEntity.ok();
    }

    @PostMapping("/verificationCode/verify")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<Void> verify(@RequestBody UserVerification user) {
        userVerificationService.verifyCode(user);
        return (ResponseEntity<Void>) ResponseEntity.ok();
    }

}
