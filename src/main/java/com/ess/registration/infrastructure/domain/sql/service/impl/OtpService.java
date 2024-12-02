//package com.ess.registration.infrastructure.domain.sql.service.impl;
//
//import com.twilio.http.TwilioRestClient;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.rest.messaging.v1.service.PhoneNumber;
//import lombok.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class OtpService {
//
//    @Value("${twilio.phoneNumber}")
//    private String twilioPhoneNumber;
//
//    private final TwilioRestClient twilioRestClient;
//
//    public OtpService(@Value("${twilio.accountSid}") String accountSid,
//                      @Value("${twilio.authToken}") String authToken) {
//        twilioRestClient = new TwilioRestClient.Builder(accountSid, authToken).build();
//    }
//
//    public String generateOtp() {
//        // Generate a random OTP code (implementation details can vary)
//    }
//
//    public void sendOtp(String recipientPhoneNumber, String otpCode) {
//        Message.creator(new PhoneNumber(recipientPhoneNumber), new PhoneNumber(twilioPhoneNumber),
//                        "Your OTP code is: " + otpCode)
//                .create(twilioRestClient);
//    }
//}
