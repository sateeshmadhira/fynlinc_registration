package com.ess.registration.core.dto;

import com.ess.registration.core.utils.OrganizationType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistrationDto {

    private Long registrationId;
    private String organizationName;
    private OrganizationType organizationType;
    private PartnerShipDto partnership;
    private PrivateLimitedDto privateLimited;
    private ProprietorShipDto proprietorship;
    private Long aadhaarNumber;
    private String panNumber;
    private String cancelledCheque;
    private String turnOver;
    private String gstNumber;
    private String city;
    private String state;
    private int zipCode;
    private int delFlag=1;

//    // Method to populate the specific child DTO based on organization type
//    public void setOrganizationDetails(Object childDetails) {
//        if (childDetails instanceof PartnerShipDto) {
//            this.partnership = (PartnerShipDto) childDetails;
//        } else if (childDetails instanceof PrivateLimitedDto) {
//            this.privateLimited = (PrivateLimitedDto) childDetails;
//        } else if (childDetails instanceof ProprietorShipDto) {
//            this.proprietorship = (ProprietorShipDto) childDetails;
//        }
 //   }
}
