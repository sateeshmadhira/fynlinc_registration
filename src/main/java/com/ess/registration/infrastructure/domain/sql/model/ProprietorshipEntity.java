package com.ess.registration.infrastructure.domain.sql.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "SOLO")
public class ProprietorshipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SOLO_ID")
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
    @Column(name = "KYC")
    private byte[] KYC;

    @OneToOne
    @JoinColumn(name = "ORGANIZATION_ID")
    private OrganizationDetailsEntity organizationDetailsEntity;
}
