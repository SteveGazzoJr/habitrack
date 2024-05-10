package com.projectdave.habitrack.controller;

import com.projectdave.habitrack.model.CodeRequest;
import com.projectdave.habitrack.model.User;
import com.projectdave.habitrack.model.UserVerificationEntity;
import com.projectdave.habitrack.model.InitiateVerificationRequest;
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
    public ResponseEntity<User> sendVerificationCode(@RequestBody InitiateVerificationRequest request) {
        return ResponseEntity.ok().body(userVerificationService.sendVerificationCode(request));
    }

    @PostMapping("/verificationCode/verify")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<Void> verify(@RequestBody CodeRequest codeRequest) {
        userVerificationService.verifyCode(codeRequest);
        return ResponseEntity.ok().build();
    }

}
