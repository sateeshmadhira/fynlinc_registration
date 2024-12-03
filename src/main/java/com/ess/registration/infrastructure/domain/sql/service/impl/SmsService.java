package com.ess.registration.infrastructure.domain.sql.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsService {

    @Value("${fast2sms.api-key}")
    private String apiKey;

    public void sendSms(String phoneNumber, String message) {
        String apiEndpoint = "https://www.fast2sms.com/dev/bulkV2";
        String requestUrl = String.format(
                "%s?authorization=%s&route=q&message=%s&language=english&flash=0&numbers=%s",
                apiEndpoint, apiKey, message, phoneNumber
        );

        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForObject(requestUrl, String.class);
            System.out.println("OTP sent successfully to " + phoneNumber);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send SMS: " + e.getMessage(), e);
        }
    }
}
