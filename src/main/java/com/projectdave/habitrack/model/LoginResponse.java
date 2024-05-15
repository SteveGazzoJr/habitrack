package com.projectdave.habitrack.model;

import lombok.Data;

@Data
public class LoginResponse {
    private User user;
    private String token;
}
