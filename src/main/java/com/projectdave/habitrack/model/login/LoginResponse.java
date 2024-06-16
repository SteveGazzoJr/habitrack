package com.projectdave.habitrack.model.login;

import com.projectdave.habitrack.model.user.User;
import lombok.Data;

@Data
public class LoginResponse {
    private User user;
    private String token;
}
