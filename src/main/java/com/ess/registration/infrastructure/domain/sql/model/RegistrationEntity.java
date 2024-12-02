package com.ess.registration.infrastructure.domain.sql.model;

import com.ess.registration.core.dto.*;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "REGISTRATION")
public class RegistrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REGISTRATION_ID")
    private Long registrationId;

    @OneToOne(mappedBy = "registrationEntity", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private OrganizationDetailsEntity organizationDetailsEntity;

    @OneToOne(mappedBy = "registrationEntity", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private CompanyTurnOverEntity companyTurnOverEntity;

    @OneToOne(mappedBy = "registrationEntity", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private AadhaarPanDetailsEntity aadhaarPanDetailsEntity;

    @OneToOne(mappedBy = "registrationEntity", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private AuthorizedPersonDetailsEntity authorizedPersonDetailsEntity;

    @OneToOne(mappedBy = "registrationEntity", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private GSTValidationsEntity gstValidationsEntity;

    @OneToOne(mappedBy = "registrationEntity", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private LocationDetailsEntity locationDetailsEntity;

    @OneToOne(mappedBy = "registrationEntity", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private LoginCredentialsEntity loginCredentialsEntity;
}

