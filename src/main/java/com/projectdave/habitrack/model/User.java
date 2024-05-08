package com.projectdave.habitrack.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("User")
@Data
public class User {
    private String id;
    private String displayName;
    private String email;
    private String phone;
    private ContactMethod preference;
    private Role role;
    private boolean smsVerified;
    private boolean emailVerified;
}
