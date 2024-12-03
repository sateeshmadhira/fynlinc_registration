package com.ess.registration.infrastructure.domain.sql.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "PARTNERSHIP")
public class PartnershipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARTNERSHIP_ID")
    private Long partnerId;

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
    @Column(name = "PARTNERSHIP_DEED")
    private String partnershipDeed;

    @Lob
    @Column(name = "KYC")
    private String KYC;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "ORGANIZATION_ID")
    private OrganizationDetailsEntity organizationDetailsEntity;
}
