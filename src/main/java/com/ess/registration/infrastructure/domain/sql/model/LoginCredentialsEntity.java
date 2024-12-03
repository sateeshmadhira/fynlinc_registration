package com.ess.registration.infrastructure.domain.sql.model;

import jakarta.persistence.*;
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
    private String email;

    @Column(name = "SET_PASSWORD")
    private String setPassword;

    @Column(name = "CONFIRM_PASSWORD")
    private String confirmPassword;
}
