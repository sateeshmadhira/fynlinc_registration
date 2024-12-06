package com.ess.registration.infrastructure.domain.sql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotNull(message = "Aadhaar Number is required")
    @Digits(integer = 12, fraction = 0, message = "Aadhaar Number should be a 12-digit number")
    private Long aadhaarNumber;

    @Column(name = "PAN_NUMBER")
    @NotNull(message = "PAN Number is required")
    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "PAN Number should be in the format XXXXX9999X")
    private String panNumber;

    @Lob
    @Column(name = "CANCELLED_CHEQUE")
    private String cancelledCheque;
}
