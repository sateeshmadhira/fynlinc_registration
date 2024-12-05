package com.ess.registration.infrastructure.controller;

import com.ess.registration.core.constants.RegistrationConstants;
import com.ess.registration.infrastructure.domain.sql.service.impl.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RegistrationConstants.AUTH_BASE_URL)
public class AuthController {

    @Autowired
    private OTPService otpService;

    @PostMapping(RegistrationConstants.GENERATE)
    public ResponseEntity<?> generateOtp(@RequestParam String phoneNumber, @RequestParam String name) {
        try {
            otpService.generateAndSendOtp(phoneNumber, name);
            return ResponseEntity.ok("OTP sent successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @PostMapping(RegistrationConstants.VERIFY)
    public ResponseEntity<?> verifyOtp(@RequestParam String phoneNumber, @RequestParam String otpCode) {
        try {
            otpService.verifyOtp(phoneNumber, otpCode);
            return ResponseEntity.ok("OTP verified successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
        }
    }
}
