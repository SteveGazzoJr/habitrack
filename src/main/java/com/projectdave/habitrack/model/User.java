package com.projectdave.habitrack.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("User")
public class User {
    private String id;
    private String displayName;
    private String email;
    private String phone;
    private ContactMethod preference;
    private Role role;
    private boolean smsVerified;
    private boolean emailVerified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ContactMethod getPreference() {
        return preference;
    }

    public void setPreference(ContactMethod preference) {
        this.preference = preference;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isSmsVerified() {
        return smsVerified;
    }

    public void setSmsVerified(boolean smsVerified) {
        this.smsVerified = smsVerified;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }
}
