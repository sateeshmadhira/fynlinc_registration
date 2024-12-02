package com.ess.registration.core.dto;

import com.ess.registration.core.utils.OrganizationType;
import lombok.Data;

@Data
public class OrganizationDetailsDto  {

    private Long id;
    private Long refId;
    private String organizationName;
    private OrganizationType organizationType;
    private PartnerShipDto partnership;
    private PrivateLimitedDto privateLimited;
    private ProprietorShipDto proprietorship;

    // Method to populate the specific child DTO based on organization type
    public void setOrganizationDetails(Object childDetails) {
        if (childDetails instanceof PartnerShipDto) {
            this.partnership = (PartnerShipDto) childDetails;
        } else if (childDetails instanceof PrivateLimitedDto) {
            this.privateLimited = (PrivateLimitedDto) childDetails;
        } else if (childDetails instanceof ProprietorShipDto) {
            this.proprietorship = (ProprietorShipDto) childDetails;
        }
    }
}
