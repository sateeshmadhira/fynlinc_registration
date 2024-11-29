package com.ess.registration.core.dto;

import lombok.Data;

@Data
public class PrivateLimitedDto {
    private Long id;
    private byte[] accountStatement;
    private byte[] ITRS;
    private byte[] GSTReturns;
    private byte[] GSTCertificate;
    private byte[] companyPAN;
    private byte[] certificateOfIncorporation;
    private byte[] AOA;
    private byte[] MOA;
    private byte[] latestShareHoldingPattern;
    private byte[] KYC;
}
