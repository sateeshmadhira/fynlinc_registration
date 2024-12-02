package com.ess.registration.core.dto;

import lombok.Data;

@Data
public class ProprietorShipDto {
    private Long id;
    private Long refId;
    private byte[] accountStatement;
    private byte[] ITRS;
    private byte[] GSTReturns;
    private byte[] GSTCertificate;
    private byte[] KYC;
}
