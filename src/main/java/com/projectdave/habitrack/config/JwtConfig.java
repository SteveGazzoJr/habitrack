package com.projectdave.habitrack.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class JwtConfig {

    @Value("${jwt.secret-key}")
    private String secretKey;

}
