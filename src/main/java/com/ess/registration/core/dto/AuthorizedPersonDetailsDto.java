package com.ess.registration.core.dto;

import lombok.Data;

@Data
public class AuthorizedPersonDetailsDto {
    private Long id;
    private Long refId;
    private String name;
    private String mobileNumber;
    private int otp;
}
