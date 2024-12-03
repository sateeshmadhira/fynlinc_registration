package com.ess.registration.infrastructure.domain.sql.model;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data

public class AuthorizedPersonDetailsEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUPD_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "REGISTRATION_ID", referencedColumnName = "REGISTRATION_ID")
    private RegistrationEntity registrationEntity;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "OTP")
    private String otp;

    @Column(name = "START_TIME")
    @Timestamp
    private LocalDateTime expiryDate;
}
