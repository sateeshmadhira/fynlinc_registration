package com.ess.registration.infrastructure.controller;

import com.ess.registration.infrastructure.domain.sql.service.impl.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/otp")
public class AuthController {

    @Autowired
    private OTPService otpService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateOtp(@RequestParam String mobileNumber,@RequestParam String name) {
        try {
            otpService.generateAndSendOtp(mobileNumber,name);
            return ResponseEntity.ok("OTP sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestParam String mobileNumber, @RequestParam String otpCode) {
        try {
            if (otpService.verifyOtp(mobileNumber, otpCode)) {
                return ResponseEntity.ok("OTP verified successfully.");
            }
            return ResponseEntity.badRequest().body("OTP verification failed.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
