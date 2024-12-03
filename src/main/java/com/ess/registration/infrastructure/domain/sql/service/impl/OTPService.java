package com.ess.registration.infrastructure.domain.sql.service.impl;

import com.ess.registration.core.dto.AuthorizedPersonDetailsDto;
import com.ess.registration.infrastructure.domain.sql.model.AuthorizedPersonDetailsEntity;
import com.ess.registration.infrastructure.domain.sql.repository.AuthorizedPersonDetailsRepository;
import com.ess.registration.infrastructure.domain.sql.service.handler.Mapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OTPService {

    @Autowired
    private AuthorizedPersonDetailsRepository authorizedPersonDetailsRepository;

    @Autowired
    private SmsService smsService;

    @Autowired
    private Mapper mapper;

    private static final int OTP_EXPIRY_DURATION_MINUTES = 5;

    // Generate and send OTP
    @Transactional
    public AuthorizedPersonDetailsDto generateAndSendOtp(String mobileNumber, String name) {
        // Check if an OTP already exists for the provided mobile number and expire it if it does
        expirePreviousOtpIfExists(mobileNumber);

        // Generate a new OTP
        String otpCode = generateOtpCode();
        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(OTP_EXPIRY_DURATION_MINUTES);

        // Create DTO and map it to the entity
        AuthorizedPersonDetailsDto authorizedPersonDetailsDto = new AuthorizedPersonDetailsDto();
        authorizedPersonDetailsDto.setOtp(otpCode);
        authorizedPersonDetailsDto.setMobileNumber(mobileNumber);
        authorizedPersonDetailsDto.setName(name);
        authorizedPersonDetailsDto.setExpiryDate(expiryDate);

        // Map DTO to Entity and save to DB
        AuthorizedPersonDetailsEntity entity = mapper.toAuthEntity(authorizedPersonDetailsDto);
        AuthorizedPersonDetailsEntity savedEntity = authorizedPersonDetailsRepository.save(entity);

        // Send OTP via SMS
        String message = "Your OTP code is: " + otpCode;
        smsService.sendSms(mobileNumber, message);

        // Convert saved entity back to DTO and return
        AuthorizedPersonDetailsDto savedDto = mapper.toAuthDto(savedEntity);
        return savedDto;
    }

    // Generate a random OTP code
    private String generateOtpCode() {
        Random random = new Random();
        return String.valueOf(100000 + random.nextInt(900000));
    }

    // Verify the provided OTP
    public boolean verifyOtp(String mobileNumber, String otpCode) {
        // Fetch the most recent OTP record for the mobile number
        AuthorizedPersonDetailsEntity otpEntity = authorizedPersonDetailsRepository.findTopByMobileNumberOrderByExpiryDateDesc(mobileNumber)
                .orElseThrow(() -> new IllegalArgumentException("No OTP found for the provided phone number"));

        // Check if the OTP has expired
        if (otpEntity.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("OTP has expired.");
        }

        // Check if the OTP code matches the saved code
        if (!otpEntity.getOtp().equals(otpCode)) {
            throw new IllegalArgumentException("Invalid OTP code.");
        }

        // Optionally invalidate OTP after successful verification
        invalidateOtp(mobileNumber);

        // OTP is valid
        return true;
    }

    // Expire the previous OTP if it exists for the given mobile number
    @Transactional
    private void expirePreviousOtpIfExists(String mobileNumber) {
        // Fetch the most recent OTP record for the mobile number
        AuthorizedPersonDetailsEntity existingOtp = authorizedPersonDetailsRepository.findTopByMobileNumberOrderByExpiryDateDesc(mobileNumber)
                .orElse(null);

        // If an OTP exists, invalidate it
        if (existingOtp != null) {
            existingOtp.setOtp(null);  // Invalidate OTP
            existingOtp.setExpiryDate(null);  // Invalidate expiry date
            authorizedPersonDetailsRepository.save(existingOtp);  // Save changes to invalidate the OTP
        }
    }

    // Invalidate OTP after successful verification
    @Transactional
    public void invalidateOtp(String mobileNumber) {
        // Fetch the most recent OTP record for the mobile number
        AuthorizedPersonDetailsEntity otpEntity = authorizedPersonDetailsRepository.findTopByMobileNumberOrderByExpiryDateDesc(mobileNumber)
                .orElseThrow(() -> new IllegalArgumentException("No OTP found for the provided phone number"));

        otpEntity.setOtp(null);  // Invalidate OTP
        otpEntity.setExpiryDate(null);  // Invalidate expiry date
        authorizedPersonDetailsRepository.save(otpEntity);  // Save changes to invalidate the OTP
    }
}
