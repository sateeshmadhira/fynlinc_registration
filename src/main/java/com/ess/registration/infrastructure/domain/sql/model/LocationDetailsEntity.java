package com.ess.registration.infrastructure.domain.sql.model;

import jakarta.persistence.*;
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
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ZIP_CODE")
    private int zipCode;
}
