package com.projectdave.habitrack.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("User")
public class User {
    private String id;
    private String email;
    private String phone;
    private ContactMethod preference;
    private Role role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
