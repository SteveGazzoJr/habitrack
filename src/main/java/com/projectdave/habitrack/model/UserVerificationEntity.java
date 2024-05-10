package com.projectdave.habitrack.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class UserVerificationEntity {
    @Id
    private String sessionId;
    private String userId;
    private String verificationCode;
    private LocalDateTime expiration;
}
