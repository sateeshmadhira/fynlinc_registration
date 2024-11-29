package com.ess.registration.core.dto;

import lombok.Data;

@Data
public class LoginCredentialsDto {

    private Long id;
    private String email;
    private String setPassword;
    private String confirmPassword;
}
