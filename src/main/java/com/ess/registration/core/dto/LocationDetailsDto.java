package com.ess.registration.core.dto;

import lombok.Data;

@Data
public class LocationDetailsDto  {
    private Long id;
    private Long refId;
    private String city;
    private String state;
    private int zipCode;
}
