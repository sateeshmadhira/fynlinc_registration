package com.ess.registration.infrastructure.domain.sql.model;

import com.ess.registration.core.utils.OrganizationType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "REGISTRATION")
public class OrganizationDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORGANIZATION_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "REGISTRATION_ID", referencedColumnName = "REGISTRATION_ID")
    private RegistrationEntity registrationEntity;

    @Column(name = "ORG_NAME")
    private String organizationName;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORG_TYPE")
    private OrganizationType organizationType;

    // Mapping to child entities (one-to-one relationship)
    @OneToOne(mappedBy = "organizationDetailsEntity",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private PartnershipEntity partnershipEntity;

    @OneToOne(mappedBy = "organizationDetailsEntity",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private PrivateLimitedEntity privateLimitedEntity;

    @OneToOne(mappedBy = "organizationDetailsEntity",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private ProprietorshipEntity proprietorshipEntity;

    // Dynamically return the correct child entity
    public Object getOrganizationDetails() {
        if (organizationType == null) {
            throw new IllegalStateException("OrganizationType is null. Cannot determine child entity.");
        }

        return switch (organizationType) {
            case PARTNERSHIP -> partnershipEntity;
            case PRIVATE_LIMITED -> privateLimitedEntity;
            case SOLO_PROPRIETORSHIP -> proprietorshipEntity;
            default -> null;
        };
    }

    // Validation to ensure consistency between organization type and child entities
    @PrePersist
    @PreUpdate
    private void validateOrganizationType() {
        if (organizationType == OrganizationType.PARTNERSHIP && partnershipEntity == null) {
            throw new IllegalStateException("PartnershipEntity must be provided for PARTNERSHIP type.");
        }
        if (organizationType == OrganizationType.PRIVATE_LIMITED && privateLimitedEntity == null) {
            throw new IllegalStateException("PrivateLimitedEntity must be provided for PRIVATE_LIMITED type.");
        }
        if (organizationType == OrganizationType.SOLO_PROPRIETORSHIP && proprietorshipEntity == null) {
            throw new IllegalStateException("ProprietorshipEntity must be provided for SOLO_PROPRIETORSHIP type.");
        }
    }
}
