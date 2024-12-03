package com.ess.registration.infrastructure.domain.sql.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "PRIVATELTD")
public class PrivateLimitedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRIVATE_LTD_ID")
    private Long id;

    @Lob
    @Column(name = "ACCOUNT_STATEMENT")
    private String accountStatement;

    @Lob
    @Column(name = "ITRS")
    private String ITRS;

    @Lob
    @Column(name = "GST_RETURNS")
    private String GSTReturns;

    @Lob
    @Column(name = "GST_CERTIFICATE")
    private String GSTCertificate;

    @Lob
    @Column(name = "COMPANY_PAN")
    private String companyPAN;

    @Lob
    @Column(name = "CERTIFICATE_OF_INCORPORATION")
    private String certificateOfIncorporation;

    @Lob
    @Column(name = "AOA")
    private String AOA;

    @Lob
    @Column(name = "MOA")
    private String MOA;

    @Lob
    @Column(name = "LATEST_SHARE_HOLDING_PATTERN")
    private String latestShareHoldingPattern;

    @Lob
    @Column(name = "KYC")
    private String KYC;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "ORGANIZATION_ID")
    private OrganizationDetailsEntity organizationDetailsEntity;
}
