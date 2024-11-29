package com.ess.registration.core.dto;

import lombok.Data;

@Data
public class AuthorizedPersonDetails {
    private Long id;
    private String name;
    private String mobileNumber;
    private int otp;
}
