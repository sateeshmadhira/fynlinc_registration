package com.ess.registration.infrastructure.domain.sql.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "REGISTRATION")
public class AadhaarPanDetailsEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "REGISTRATION_ID", referencedColumnName = "REGISTRATION_ID")
    private RegistrationEntity registrationEntity;

    @Column(name = "AADHAAR_NUMBER")
    private Long aadhaarNumber;

    @Column(name = "PAN_NUMBER")
    private String panNumber;

    @Lob
    @Column(name = "CANCELLED_CHEQUE")
    private byte[] cancelledCheque;
}