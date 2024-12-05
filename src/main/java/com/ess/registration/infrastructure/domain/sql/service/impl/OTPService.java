package com.ess.registration.infrastructure.domain.sql.service.impl;

import com.ess.registration.core.dto.AuthorizedPersonDetailsDto;
import com.ess.registration.infrastructure.domain.sql.model.AuthorizedPersonDetailsEntity;
import com.ess.registration.infrastructure.domain.sql.repository.AuthorizedPersonDetailsRepository;
import com.ess.registration.infrastructure.domain.sql.service.handler.Mapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OTPService {

    private final AuthorizedPersonDetailsRepository authorizedPersonDetailsRepository;
    private final SmsService smsService;
    private final Mapper mapper;

    private static final int OTP_EXPIRY_DURATION_MINUTES = 2;

    public OTPService(AuthorizedPersonDetailsRepository authorizedPersonDetailsRepository,
                      SmsService smsService,
                      Mapper mapper) {
        this.authorizedPersonDetailsRepository = authorizedPersonDetailsRepository;
        this.smsService = smsService;
        this.mapper = mapper;
    }

    @Transactional
    public AuthorizedPersonDetailsDto generateAndSendOtp(String phoneNumber, String name) {
        // Check if there's a valid OTP already
        AuthorizedPersonDetailsEntity existingOtp = authorizedPersonDetailsRepository
                .findTopByPhoneNumberOrderByExpiryDateDesc(phoneNumber)
                .orElse(null);

        if (existingOtp != null && existingOtp.getExpiryDate().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("OTP already sent. Please wait until it expires.");
        }

        // Generate and send new OTP
        String otpCode = generateOtpCode();
        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(OTP_EXPIRY_DURATION_MINUTES);

        AuthorizedPersonDetailsDto authorizedPersonDetailsDto = new AuthorizedPersonDetailsDto();
        authorizedPersonDetailsDto.setOtp(otpCode);
        authorizedPersonDetailsDto.setPhoneNumber(phoneNumber);
        authorizedPersonDetailsDto.setName(name);
        authorizedPersonDetailsDto.setExpiryDate(expiryDate);

        AuthorizedPersonDetailsEntity entity = mapper.toAuthEntity(authorizedPersonDetailsDto);
        AuthorizedPersonDetailsEntity savedEntity = authorizedPersonDetailsRepository.save(entity);

        // Send OTP via SMS
        smsService.sendSms(phoneNumber, otpCode);

        return mapper.toAuthDto(savedEntity);
    }

    private String generateOtpCode() {
        Random random = new Random();
        return String.valueOf(100000 + random.nextInt(900000)); // 6-digit OTP
    }

    public boolean verifyOtp(String phoneNumber, String otpCode) {
        AuthorizedPersonDetailsEntity otpEntity = authorizedPersonDetailsRepository
                .findTopByPhoneNumberOrderByExpiryDateDesc(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("No OTP found for the provided phone number"));

        if (otpEntity.getExpiryDate().isBefore(LocalDateTime.now())) {
            System.out.println("test purpose"+otpEntity.getExpiryDate().isBefore(LocalDateTime.now()));
            throw new IllegalArgumentException("OTP has expired.");
        }

        if (!otpEntity.getOtp().equals(otpCode)) {
            throw new IllegalArgumentException("Invalid OTP code.");
        }

        return true;
    }
}
