package com.ess.registration.infrastructure.domain.sql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "PRIVATELTD")
public class PrivateLimitedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRIVATE_LTD_ID")
    private Long id;


    @NotBlank(message = "Account statement cannot be blank")
    @Column(name = "ACCOUNT_STATEMENT", nullable = false)
    private String accountStatement;


    @NotBlank(message = "ITRs cannot be blank")
    @Column(name = "ITRS", nullable = false)
    private String ITRS;


    @NotBlank(message = "GST returns cannot be blank")
    @Column(name = "GST_RETURNS", nullable = false)
    private String GSTReturns;


    @NotBlank(message = "GST certificate cannot be blank")
    @Column(name = "GST_CERTIFICATE", nullable = false)
    private String GSTCertificate;


    @NotBlank(message = "Company PAN cannot be blank")
    @Column(name = "COMPANY_PAN", nullable = false)
    private String companyPAN;


    @NotBlank(message = "Certificate of incorporation cannot be blank")
    @Column(name = "CERTIFICATE_OF_INCORPORATION", nullable = false)
    private String certificateOfIncorporation;


    @NotBlank(message = "AOA cannot be blank")
    @Column(name = "AOA", nullable = false)
    private String AOA;


    @NotBlank(message = "MOA cannot be blank")
    @Column(name = "MOA", nullable = false)
    private String MOA;


    @NotBlank(message = "Latest shareholding pattern cannot be blank")
    @Column(name = "LATEST_SHARE_HOLDING_PATTERN", nullable = false)
    private String latestShareHoldingPattern;


    @NotBlank(message = "KYC cannot be blank")
    @Column(name = "KYC", nullable = false)
    private String KYC;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "REGISTRATION_ID")
    private RegistrationEntity registrationEntity;
}
