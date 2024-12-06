package com.ess.registration.infrastructure.domain.sql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data

public class LocationDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "REGISTRATION_ID", referencedColumnName = "REGISTRATION_ID")
    private RegistrationEntity registrationEntity;

    @Column(name = "CITY")
    @NotNull(message = "City is required")
    @Size(min = 2, max = 50, message = "City name should be between 2 and 50 characters")
    private String city;

    @Column(name = "STATE")
    @NotNull(message = "State is required")
    @Size(min = 2, max = 50, message = "State name should be between 2 and 50 characters")
    private String state;

    @Column(name = "ZIP_CODE")
    @NotNull(message = "Zip Code is required")
    @Min(value = 100000, message = "Zip code must be a valid 6-digit number")
    @Max(value = 999999, message = "Zip code must be a valid 6-digit number")
    private int zipCode;
}
