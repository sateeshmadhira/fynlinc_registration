package com.ess.registration.core.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthorizedPersonDetailsDto {
    private Long id;
    private Long refId;
    private String name;
    private String phoneNumber;
    private String otp;
    private LocalDateTime expiryDate;
}
