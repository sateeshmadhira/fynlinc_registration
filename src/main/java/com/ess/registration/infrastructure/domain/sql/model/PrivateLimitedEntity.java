package com.ess.registration.infrastructure.domain.sql.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "PRIVATELTD")
public class PrivateLimitedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Lob
    @Column(name = "ACCOUNT_STATEMENT")
    private byte[] accountStatement;

    @Lob
    @Column(name = "ITRS")
    private byte[] ITRS;

    @Lob
    @Column(name = "GST_RETURNS")
    private byte[] GSTReturns;

    @Lob
    @Column(name = "GST_CERTIFICATE")
    private byte[] GSTCertificate;

    @Lob
    @Column(name = "COMPANY_PAN")
    private byte[] companyPAN;

    @Lob
    @Column(name = "CERTIFICATE_OF_INCORPORATION")
    private byte[] certificateOfIncorporation;

    @Lob
    @Column(name = "AOA")
    private byte[] AOA;

    @Lob
    @Column(name = "MOA")
    private byte[] MOA;

    @Lob
    @Column(name = "LATEST_SHARE_HOLDING_PATTERN")
    private byte[] latestShareHoldingPattern;

    @Lob
    @Column(name = "KYC")
    private byte[] KYC;

    @OneToOne
    @JoinColumn(name = "ORGANIZATION_ID")
    private OrganizationDetailsEntity organizationDetailsEntity;
}
