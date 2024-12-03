package com.ess.registration.core.dto;

import lombok.Data;

@Data
public class PartnerShipDto {
    private Long id;
    private Long refId;
    private String accountStatement;
    private String ITRS;
    private String GSTReturns;
    private String GSTCertificate;
    private String companyPAN;
    private String partnershipDeed;
    private String KYC;


}
