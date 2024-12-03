package com.ess.registration.infrastructure.domain.sql.service.handler;

import com.ess.registration.core.dto.*;
import com.ess.registration.core.utils.OrganizationType;
import com.ess.registration.infrastructure.domain.sql.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperImpl implements Mapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RegistrationEntity toEntity(RegistrationDto registrationDto) {
        RegistrationEntity registrationEntity = modelMapper.map(registrationDto, RegistrationEntity.class);
        if(registrationDto.getOrganizationDetailsDto()!=null){
            OrganizationDetailsEntity organizationDetailsEntity = modelMapper.map(registrationDto.getOrganizationDetailsDto(), OrganizationDetailsEntity.class);
            // Organization type-specific logic
            OrganizationType organizationType = registrationDto.getOrganizationDetailsDto().getOrganizationType();
            switch (organizationType) {
                case PARTNERSHIP -> {
                    PartnershipEntity partnershipEntity = modelMapper.map(registrationDto.getOrganizationDetailsDto().getPartnership(), PartnershipEntity.class);
                    partnershipEntity.setOrganizationDetailsEntity(organizationDetailsEntity);
                    organizationDetailsEntity.setPartnershipEntity(partnershipEntity);
                    organizationDetailsEntity.setPrivateLimitedEntity(null);
                    organizationDetailsEntity.setProprietorshipEntity(null);
                }
                case PRIVATE_LIMITED -> {
                    PrivateLimitedEntity privateLimitedEntity = modelMapper.map(registrationDto.getOrganizationDetailsDto().getPrivateLimited(), PrivateLimitedEntity.class);
                    privateLimitedEntity.setOrganizationDetailsEntity(organizationDetailsEntity);
                    organizationDetailsEntity.setPrivateLimitedEntity(privateLimitedEntity);
                    organizationDetailsEntity.setPartnershipEntity(null);
                    organizationDetailsEntity.setProprietorshipEntity(null);
                }
                case SOLO_PROPRIETORSHIP -> {
                    ProprietorshipEntity proprietorshipEntity = modelMapper.map(registrationDto.getOrganizationDetailsDto().getProprietorship(), ProprietorshipEntity.class);
                    proprietorshipEntity.setOrganizationDetailsEntity(organizationDetailsEntity);
                    organizationDetailsEntity.setProprietorshipEntity(proprietorshipEntity);
                    organizationDetailsEntity.setPartnershipEntity(null);
                    organizationDetailsEntity.setPrivateLimitedEntity(null);
                }
            }
            organizationDetailsEntity.setRegistrationEntity(registrationEntity);
            registrationEntity.setOrganizationDetailsEntity(organizationDetailsEntity);
        }
        if (registrationDto.getAadhaarPanDetailsDto()!=null){
            AadhaarPanDetailsEntity aadhaarPanDetailsEntity=modelMapper.map(registrationDto.getAadhaarPanDetailsDto(), AadhaarPanDetailsEntity.class);
            aadhaarPanDetailsEntity.setRegistrationEntity(registrationEntity);
            registrationEntity.setAadhaarPanDetailsEntity(aadhaarPanDetailsEntity);
        }
        if (registrationDto.getGstValidationsDto()!=null){
            GSTValidationsEntity gstValidationsEntity=modelMapper.map(registrationDto.getGstValidationsDto(), GSTValidationsEntity.class);
            gstValidationsEntity.setRegistrationEntity(registrationEntity);
            registrationEntity.setGstValidationsEntity(gstValidationsEntity);
        }
        if (registrationDto.getLocationDetailsDto()!=null){
            LocationDetailsEntity locationDetailsEntity=modelMapper.map(registrationDto.getLocationDetailsDto(), LocationDetailsEntity.class);
            locationDetailsEntity.setRegistrationEntity(registrationEntity);
            registrationEntity.setLocationDetailsEntity(locationDetailsEntity);
        }
        if (registrationDto.getLoginCredentialsDto()!=null){
            LoginCredentialsEntity loginCredentialsEntity=modelMapper.map(registrationDto.getLoginCredentialsDto(), LoginCredentialsEntity.class);
            loginCredentialsEntity.setRegistrationEntity(registrationEntity);
            registrationEntity.setLoginCredentialsEntity(loginCredentialsEntity);
        }
        if (registrationDto.getCompanyTurnOverDto() != null) {
            CompanyTurnOverEntity companyTurnOverEntity=modelMapper.map(registrationDto.getCompanyTurnOverDto(),CompanyTurnOverEntity.class);
            companyTurnOverEntity.setRegistrationEntity(registrationEntity);
            registrationEntity.setCompanyTurnOverEntity(companyTurnOverEntity);
        }
        if(registrationDto.getAuthorizedPersonDetailsDto() != null) {
            AuthorizedPersonDetailsEntity authorizedPersonDetailsEntity = modelMapper.map(registrationDto.getAuthorizedPersonDetailsDto(), AuthorizedPersonDetailsEntity.class);
            authorizedPersonDetailsEntity.setRegistrationEntity(registrationEntity);
            registrationEntity.setAuthorizedPersonDetailsEntity(authorizedPersonDetailsEntity);
        }
        return registrationEntity;
    }

    @Override
    public RegistrationDto toDto(RegistrationEntity registrationEntity) {
        RegistrationDto registrationDto = modelMapper.map(registrationEntity, RegistrationDto.class);
        if (registrationEntity.getAadhaarPanDetailsEntity() != null) {
            AadhaarPanDetailsDto aadhaarPanDetailsDto = modelMapper.map(registrationEntity.getAadhaarPanDetailsEntity(), AadhaarPanDetailsDto.class);
            aadhaarPanDetailsDto.setRefId(registrationEntity.getRegistrationId());
            registrationDto.setAadhaarPanDetailsDto(aadhaarPanDetailsDto);
        }
        if (registrationEntity.getOrganizationDetailsEntity() != null) {
            OrganizationDetailsDto organizationDetailsDto = modelMapper.map(registrationEntity.getOrganizationDetailsEntity(), OrganizationDetailsDto.class);
            OrganizationType organizationType = registrationEntity.getOrganizationDetailsEntity().getOrganizationType();
            switch (organizationType) {
                case PARTNERSHIP -> {
                    if (registrationEntity.getOrganizationDetailsEntity().getPartnershipEntity() != null) {
                        PartnerShipDto partnershipDto = modelMapper.map(registrationEntity.getOrganizationDetailsEntity().getPartnershipEntity(), PartnerShipDto.class);
                        partnershipDto.setRefId(organizationDetailsDto.getId());
                        organizationDetailsDto.setPartnership(partnershipDto);
                        organizationDetailsDto.setProprietorship(null);
                        organizationDetailsDto.setPrivateLimited(null);
                    }
                }
                case PRIVATE_LIMITED -> {
                    if (registrationEntity.getOrganizationDetailsEntity().getPrivateLimitedEntity() != null) {
                        PrivateLimitedDto privateLimitedDto = modelMapper.map(registrationEntity.getOrganizationDetailsEntity().getPrivateLimitedEntity(), PrivateLimitedDto.class);
                        privateLimitedDto.setRefId(organizationDetailsDto.getId());
                        organizationDetailsDto.setPrivateLimited(privateLimitedDto);
                        organizationDetailsDto.setPartnership(null);
                        organizationDetailsDto.setProprietorship(null);
                    }
                }
                case SOLO_PROPRIETORSHIP -> {
                    if (registrationEntity.getOrganizationDetailsEntity().getProprietorshipEntity() != null) {
                        ProprietorShipDto proprietorshipDto = modelMapper.map(registrationEntity.getOrganizationDetailsEntity().getProprietorshipEntity(), ProprietorShipDto.class);
                        proprietorshipDto.setRefId(organizationDetailsDto.getId());
                        organizationDetailsDto.setProprietorship(proprietorshipDto);
                        organizationDetailsDto.setPartnership(null);
                        organizationDetailsDto.setPrivateLimited(null);
                    }
                }
            }
            organizationDetailsDto.setRefId(registrationEntity.getRegistrationId());
            registrationDto.setOrganizationDetailsDto(organizationDetailsDto);
        }
        if (registrationEntity.getAuthorizedPersonDetailsEntity() != null) {
            AuthorizedPersonDetailsDto authorizedPersonDetailsDto = modelMapper.map(registrationEntity.getAuthorizedPersonDetailsEntity(), AuthorizedPersonDetailsDto.class);
            authorizedPersonDetailsDto.setRefId(registrationEntity.getRegistrationId());
            registrationDto.setAuthorizedPersonDetailsDto(authorizedPersonDetailsDto);
        }
        if (registrationEntity.getCompanyTurnOverEntity() != null) {
            CompanyTurnOverDto companyTurnOverDto = modelMapper.map(registrationEntity.getCompanyTurnOverEntity(), CompanyTurnOverDto.class);
            companyTurnOverDto.setRefId(registrationEntity.getRegistrationId());
            registrationDto.setCompanyTurnOverDto(companyTurnOverDto);
        }
        if (registrationEntity.getGstValidationsEntity() != null) {
            GSTValidationsDto gstValidationsDto = modelMapper.map(registrationEntity.getGstValidationsEntity(), GSTValidationsDto.class);
            gstValidationsDto.setRefId(registrationEntity.getRegistrationId());
            registrationDto.setGstValidationsDto(gstValidationsDto);
        }
        if (registrationEntity.getLocationDetailsEntity() != null) {
            LocationDetailsDto locationDetailsDto = modelMapper.map(registrationEntity.getLocationDetailsEntity(), LocationDetailsDto.class);
            locationDetailsDto.setRefId(registrationEntity.getRegistrationId());
            registrationDto.setLocationDetailsDto(locationDetailsDto);
        }
        if (registrationEntity.getLoginCredentialsEntity() != null) {
            LoginCredentialsDto loginCredentialsDto = modelMapper.map(registrationEntity.getLoginCredentialsEntity(), LoginCredentialsDto.class);
            loginCredentialsDto.setRefId(registrationEntity.getRegistrationId());
            registrationDto.setLoginCredentialsDto(loginCredentialsDto);
        }
        return registrationDto;
    }

    @Override
    public AuthorizedPersonDetailsDto toAuthDto(AuthorizedPersonDetailsEntity entity) {
        AuthorizedPersonDetailsDto authorizedPersonDetailsDto=modelMapper.map(entity,AuthorizedPersonDetailsDto.class);
        return authorizedPersonDetailsDto;
    }

    @Override
    public AuthorizedPersonDetailsEntity toAuthEntity(AuthorizedPersonDetailsDto dto) {
        AuthorizedPersonDetailsEntity authorizedPersonDetailsEntity=modelMapper.map(dto, AuthorizedPersonDetailsEntity.class);
        return authorizedPersonDetailsEntity;
    }
}
