package com.projectdave.habitrack.model.login;

import lombok.Data;

@Data
public class CodeRequest {
    private String userId;
    private String code;
}
