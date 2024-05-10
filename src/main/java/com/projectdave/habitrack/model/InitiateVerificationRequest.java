package com.projectdave.habitrack.model;

import lombok.Data;

@Data
public class InitiateVerificationRequest {
    private String email;
    private ContactMethod preference;
}
