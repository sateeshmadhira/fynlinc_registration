package com.ess.registration.core.dto;

import lombok.Data;

@Data
public class AadhaarPanDetailsDto {

    private Long id;
    private Long refId;
    private Long aadhaarNumber;
    private String panNumber;
    private String cancelledCheque;

}
