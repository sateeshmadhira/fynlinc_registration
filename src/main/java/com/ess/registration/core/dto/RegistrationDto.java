package com.ess.registration.core.dto;

import lombok.Data;

@Data
public class RegistrationDto {

    private Long registrationId;
    private OrganizationDetailsDto organizationDetailsDto;
    private CompanyTurnOverDto companyTurnOverDto;
    private AadhaarPanDetailsDto aadhaarPanDetailsDto;
    private AuthorizedPersonDetailsDto authorizedPersonDetailsDto;
    private GSTValidationsDto gstValidationsDto;
    private LocationDetailsDto locationDetailsDto;
    private LoginCredentialsDto loginCredentialsDto;
}
