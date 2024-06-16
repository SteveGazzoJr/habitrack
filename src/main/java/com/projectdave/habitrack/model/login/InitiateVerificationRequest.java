package com.projectdave.habitrack.model.login;

import com.projectdave.habitrack.model.user.ContactMethod;
import lombok.Data;

@Data
public class InitiateVerificationRequest {
    private String email;
    private ContactMethod preference;
}
