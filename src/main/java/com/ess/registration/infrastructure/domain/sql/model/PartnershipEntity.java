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
    @Column(name = "PARTNERSHIP_DEED")
    private byte[] partnershipDeed;

    @Lob
    @Column(name = "KYC")
    private byte[] KYC;

    @OneToOne
    @JoinColumn(name = "ORGANIZATION_ID")
    private OrganizationDetailsEntity organizationDetailsEntity;
}
