package com.projectdave.habitrack.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {
    @Value("${twilio.account}")
    private String account;
    @Value("${twilio.token}")
    private String token;
    @Value("${twilio.number}")
    private String number;

    public String getAccount() {
        return account;
    }

    public String getToken() {
        return token;
    }

    public String getNumber() {
        return number;
    }
}
