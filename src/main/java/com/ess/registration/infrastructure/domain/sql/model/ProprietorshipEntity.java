package com.ess.registration.infrastructure.domain.sql.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "SOLO_PROPRIETOR")
public class ProprietorshipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SOLO_PROPRIETOR_ID")
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
    @Column(name = "KYC")
    private String KYC;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "ORGANIZATION_ID")
    private OrganizationDetailsEntity organizationDetailsEntity;
}
