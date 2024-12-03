package com.ess.registration.core.dto;

import lombok.Data;

@Data
public class PrivateLimitedDto {
    private Long id;
    private Long refId;
    private String accountStatement;
    private String ITRS;
    private String GSTReturns;
    private String GSTCertificate;
    private String companyPAN;
    private String certificateOfIncorporation;
    private String AOA;
    private String MOA;
    private String latestShareHoldingPattern;
    private String KYC;
}
