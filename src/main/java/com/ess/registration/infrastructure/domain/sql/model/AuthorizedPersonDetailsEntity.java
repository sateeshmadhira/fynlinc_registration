package com.ess.registration.infrastructure.domain.sql.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "REGISTRATION")
public class AuthorizedPersonDetailsEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "REGISTRATION_ID", referencedColumnName = "REGISTRATION_ID")
    private RegistrationEntity registrationEntity;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "OTP")
    private int otp;
}
