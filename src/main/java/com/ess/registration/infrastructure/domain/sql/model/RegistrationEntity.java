package com.ess.registration.infrastructure.domain.sql.model;

import com.ess.registration.core.utils.OrganizationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "REGISTRATION",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "REG_AADHAAR_NUMBER", name = "UNIQUE_AADHAAR_NUMBER"),
                @UniqueConstraint(columnNames = "REG_PAN_NUMBER", name = "UNIQUE_PAN_NUMBER"),
                @UniqueConstraint(columnNames = "REG_GST_NUMBER", name = "UNIQUE_GST_NUMBER")
        })
public class RegistrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REG_ID")
    private Long registrationId;

    @NotBlank(message = "Organization name cannot be blank")
    @Size(max = 100, message = "Organization name must be less than 100 characters")
    @Column(name = "REG_ORG_NAME", length = 100, nullable = false)
    private String organizationName;

    @NotNull(message = "Organization type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "REG_ORG_TYPE", nullable = false)
    private OrganizationType organizationType;

    @OneToOne(mappedBy = "registrationEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PartnershipEntity partnershipEntity;

    @OneToOne(mappedBy = "registrationEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PrivateLimitedEntity privateLimitedEntity;

    @OneToOne(mappedBy = "registrationEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ProprietorshipEntity proprietorshipEntity;

    @NotNull(message = "Aadhaar number cannot be null")
    @Digits(integer = 12, fraction = 0, message = "Aadhaar number must be a 12-digit number")
    @Column(name = "REG_AADHAAR_NUMBER", unique = true, nullable = false)
    private Long aadhaarNumber;

    @NotBlank(message = "PAN number cannot be blank")
    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN number format")
    @Column(name = "REG_PAN_NUMBER", length = 10, unique = true, nullable = false)
    private String panNumber;

    @Lob
    @NotBlank(message = "Cancelled Cheque cannot be blank")
    @Column(name = "REG_CANCELLED_CHEQUE", nullable = false)
    private String cancelledCheque;

    @Size(max = 50, message = "Turnover must be less than 50 characters")
    @Column(name = "REG_TURN_OVER", length = 50)
    private String turnOver;

    @Pattern(regexp = "\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z0-9]{3}", message = "Invalid GST number format")
    @Column(name = "REG_GST_NUMBER", length = 15, unique = true)
    private String gstNumber;

    @NotBlank(message = "City cannot be blank")
    @Size(max = 50, message = "City must be less than 50 characters")
    @Column(name = "REG_CITY", length = 50, nullable = false)
    private String city;

    @NotBlank(message = "State cannot be blank")
    @Size(max = 50, message = "State must be less than 50 characters")
    @Column(name = "REG_STATE", length = 50, nullable = false)
    private String state;

    @Positive(message = "Zip code must be a positive number")
    @Column(name = "REG_ZIP_CODE", nullable = false)
    private int zipCode;

    @Column(name = "DEL_FLAG")
    private int delFlag = 1;

    // Dynamically return the correct child entity
    public Object getOrganizationDetails() {
        if (organizationType == null) {
            throw new IllegalStateException("OrganizationType is null. Cannot determine child entity.");
        }

        return switch (organizationType) {
            case PARTNERSHIP -> partnershipEntity;
            case PRIVATE_LIMITED -> privateLimitedEntity;
            case PROPRIETORSHIP -> proprietorshipEntity;
        };
    }

    @PrePersist
    @PreUpdate
    private void validateOrganizationType() {
        if (organizationType == OrganizationType.PARTNERSHIP && partnershipEntity == null) {
            throw new IllegalStateException("PartnershipEntity must be provided for PARTNERSHIP type.");
        }
        if (organizationType == OrganizationType.PRIVATE_LIMITED && privateLimitedEntity == null) {
            throw new IllegalStateException("PrivateLimitedEntity must be provided for PRIVATE_LIMITED type.");
        }
        if (organizationType == OrganizationType.PROPRIETORSHIP && proprietorshipEntity == null) {
            throw new IllegalStateException("ProprietorshipEntity must be provided for SOLO_PROPRIETORSHIP type.");
        }
    }
}
