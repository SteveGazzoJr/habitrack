package com.projectdave.habitrack.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class TwilioConfig {
    @Value("${twilio.account}")
    private String account;
    @Value("${twilio.token}")
    private String token;
    @Value("${twilio.number}")
    private String number;
    @Value("${twilio.testEnv}")
    private boolean testEnv;
}
