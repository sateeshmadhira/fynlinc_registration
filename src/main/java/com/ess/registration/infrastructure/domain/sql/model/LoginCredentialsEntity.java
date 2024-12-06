package com.ess.registration.infrastructure.domain.sql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class LoginCredentialsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOGIN_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "REGISTRATION_ID", referencedColumnName = "REGISTRATION_ID")
    private RegistrationEntity registrationEntity;

    @Column(name = "EMAIL")
    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Column(name = "SET_PASSWORD")
    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String setPassword;

    @Column(name = "CONFIRM_PASSWORD")
    @NotNull(message = "Confirm Password is required")
    @Size(min = 6, message = "Confirm Password must be at least 6 characters long")
    private String confirmPassword;
}
