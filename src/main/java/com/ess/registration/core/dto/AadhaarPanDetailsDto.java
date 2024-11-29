package com.ess.registration.core.dto;

import lombok.Data;

@Data
public class AadhaarPanDetailsDto {

    private Long id;
    private Long aadhaarNumber;
    private String panNumber;
    private byte[] cancelledCheque;

}
