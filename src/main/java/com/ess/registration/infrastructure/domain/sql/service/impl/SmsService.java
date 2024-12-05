package com.ess.registration.infrastructure.domain.sql.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.http.ResponseEntity;

@Service
public class SmsService {

    @Value("${fast2sms.api-key}")
    private String apiKey;

    public void sendSms(String phoneNumber, String otp) {
        String apiEndpoint = "https://www.fast2sms.com/dev/bulkV2";
        String requestUrl = String.format(
                "%s?authorization=%s&route=otp&variables_values=%s&flash=0&language=english&numbers=%s",
                apiEndpoint, apiKey, otp, phoneNumber
        );

        RestTemplate restTemplate = new RestTemplate();
        try {
            // Perform the request
            ResponseEntity<String> response = restTemplate.getForEntity(requestUrl, String.class);
            String responseBody = response.getBody();
            System.out.println("Fast2SMS API Response: " + responseBody);

            if (responseBody == null || !responseBody.contains("\"return\":true")) {
                throw new RuntimeException("Failed to send SMS. API Response: " + responseBody);
            }

            System.out.println("OTP sent successfully to " + phoneNumber);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("HTTP error occurred while sending SMS: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send SMS: " + e.getMessage(), e);
        }
    }
}
