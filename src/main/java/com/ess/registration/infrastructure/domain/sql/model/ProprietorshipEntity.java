package com.ess.registration.infrastructure.domain.sql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "PROPRIETOR")
public class ProprietorshipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROPRIETOR_ID")
    private Long id;

    @Lob
    @NotBlank(message = "Account statement cannot be blank")
    @Column(name = "ACCOUNT_STATEMENT", nullable = false)
    private String accountStatement;

    @Lob
    @NotBlank(message = "ITRs cannot be blank")
    @Column(name = "ITRS", nullable = false)
    private String ITRS;

    @Lob
    @NotBlank(message = "GST returns cannot be blank")
    @Column(name = "GST_RETURNS", nullable = false)
    private String GSTReturns;

    @Lob
    @NotBlank(message = "GST certificate cannot be blank")
    @Column(name = "GST_CERTIFICATE", nullable = false)
    private String GSTCertificate;
    @Lob
    @NotBlank(message = "KYC cannot be blank")
    @Column(name = "KYC", nullable = false)
    private String KYC;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "REGISTRATION_ID")
    private RegistrationEntity registrationEntity;
}
