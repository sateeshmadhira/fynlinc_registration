package com.ess.registration.infrastructure.domain.sql.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class  AadhaarPanDetailsEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APD_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "REGISTRATION_ID")
    private RegistrationEntity registrationEntity;

    @Column(name = "AADHAAR_NUMBER")
    private Long aadhaarNumber;

    @Column(name = "PAN_NUMBER")
    private String panNumber;

    @Lob
    @Column(name = "CANCELLED_CHEQUE")
    private String cancelledCheque;
}
