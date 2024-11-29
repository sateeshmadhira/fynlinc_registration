package com.ess.registration.core.dto;

import com.ess.registration.core.utils.OrganizationType;
import lombok.Data;

@Data
public class OrganizationDetailsDto {

    private Long organizationId;
    private String organizationName;
    private OrganizationType organizationType;
}
