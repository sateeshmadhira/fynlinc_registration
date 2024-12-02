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

        mapAadhaarPanDetails(registrationDto, registrationEntity);
        mapAuthorizedPersonDetails(registrationDto, registrationEntity);
        mapCompanyTurnOverDetails(registrationDto, registrationEntity);
        mapGstValidations(registrationDto, registrationEntity);
        mapLocationDetails(registrationDto, registrationEntity);
        mapLoginCredentials(registrationDto, registrationEntity);
        mapOrganizationDetails(registrationDto, registrationEntity);

        return registrationEntity;
    }

    private void mapAadhaarPanDetails(RegistrationDto dto, RegistrationEntity entity) {
        if (dto.getAadhaarPanDetailsDto() != null) {
            AadhaarPanDetailsEntity aadhaarPanDetails = modelMapper.map(dto.getAadhaarPanDetailsDto(), AadhaarPanDetailsEntity.class);
            aadhaarPanDetails.setRegistrationEntity(entity);
            entity.setAadhaarPanDetailsEntity(aadhaarPanDetails);
        }
    }

    private void mapAuthorizedPersonDetails(RegistrationDto dto, RegistrationEntity entity) {
        if (dto.getAuthorizedPersonDetailsDto() != null) {
            AuthorizedPersonDetailsEntity authorizedPersonDetails = modelMapper.map(dto.getAuthorizedPersonDetailsDto(), AuthorizedPersonDetailsEntity.class);
            authorizedPersonDetails.setRegistrationEntity(entity);
            entity.setAuthorizedPersonDetailsEntity(authorizedPersonDetails);
        }
    }
    private void mapCompanyTurnOverDetails(RegistrationDto dto,RegistrationEntity entity) {
        if (dto.getCompanyTurnOverDto() != null) {
            CompanyTurnOverEntity companyTurnOverEntity = modelMapper.map(dto.getCompanyTurnOverDto(), CompanyTurnOverEntity.class);
            companyTurnOverEntity.setRegistrationEntity(entity);
            entity.setCompanyTurnOverEntity(companyTurnOverEntity);
        }
    }
    private void mapGstValidations(RegistrationDto dto,RegistrationEntity entity) {
        if (dto.getGstValidationsDto() != null) {
            GSTValidationsEntity gstValidationsEntity = modelMapper.map(dto.getGstValidationsDto(), GSTValidationsEntity.class);
            gstValidationsEntity.setRegistrationEntity(entity);
            entity.setGstValidationsEntity(gstValidationsEntity);
        }
    }
    private void mapLocationDetails(RegistrationDto dto,RegistrationEntity entity) {
        if (dto.getLocationDetailsDto() != null) {
            LocationDetailsEntity locationDetailsEntity = modelMapper.map(dto.getLocationDetailsDto(), LocationDetailsEntity.class);
            locationDetailsEntity.setRegistrationEntity(entity);
            entity.setLocationDetailsEntity(locationDetailsEntity);
        }
    }
    private void mapLoginCredentials(RegistrationDto dto,RegistrationEntity entity) {
        if (dto.getLoginCredentialsDto() != null) {
            LoginCredentialsEntity loginCredentialsEntity = modelMapper.map(dto.getLoginCredentialsDto(), LoginCredentialsEntity.class);
            loginCredentialsEntity.setRegistrationEntity(entity);
            entity.setLoginCredentialsEntity(loginCredentialsEntity);
        }
    }

    private void mapOrganizationDetails(RegistrationDto dto, RegistrationEntity entity) {
        if (dto.getOrganizationDetailsDto() != null) {
            OrganizationDetailsEntity organizationDetails = modelMapper.map(dto.getOrganizationDetailsDto(), OrganizationDetailsEntity.class);

            switch (dto.getOrganizationDetailsDto().getOrganizationType()) {
                case PARTNERSHIP -> {
                    PartnershipEntity partnership = modelMapper.map(dto.getOrganizationDetailsDto().getPartnership(), PartnershipEntity.class);
                    partnership.setOrganizationDetailsEntity(organizationDetails);
                    organizationDetails.setPartnershipEntity(partnership);
                }
                case PRIVATE_LIMITED -> {
                    PrivateLimitedEntity privateLimited = modelMapper.map(dto.getOrganizationDetailsDto().getPrivateLimited(), PrivateLimitedEntity.class);
                    privateLimited.setOrganizationDetailsEntity(organizationDetails);
                    organizationDetails.setPrivateLimitedEntity(privateLimited);
                }
                case SOLO_PROPRIETORSHIP -> {
                    ProprietorshipEntity proprietorship = modelMapper.map(dto.getOrganizationDetailsDto().getProprietorship(), ProprietorshipEntity.class);
                    proprietorship.setOrganizationDetailsEntity(organizationDetails);
                    organizationDetails.setProprietorshipEntity(proprietorship);
                }
            }

            organizationDetails.setRegistrationEntity(entity);
            entity.setOrganizationDetailsEntity(organizationDetails);
        }
    }

    @Override
    public RegistrationDto toDto(RegistrationEntity registrationEntity) {
        RegistrationDto registrationDto = modelMapper.map(registrationEntity, RegistrationDto.class);

        mapAadhaarPanDetailsDto(registrationEntity, registrationDto);
        mapAuthorizedPersonDetailsDto(registrationEntity, registrationDto);
        mapCompanyTurnOverDetailsDto(registrationEntity, registrationDto);
        mapGstValidationsDto(registrationEntity, registrationDto);
        mapLocationDetailsDto(registrationEntity, registrationDto);
        mapLoginCredentialsDto(registrationEntity, registrationDto);
        mapOrganizationDetailsDto(registrationEntity, registrationDto);

        return registrationDto;
    }

    private void mapAadhaarPanDetailsDto(RegistrationEntity entity, RegistrationDto dto) {
        if (entity.getAadhaarPanDetailsEntity() != null) {
            AadhaarPanDetailsDto aadhaarPanDetailsDto = modelMapper.map(entity.getAadhaarPanDetailsEntity(), AadhaarPanDetailsDto.class);
            aadhaarPanDetailsDto.setRefId(dto.getRegistrationId());
            dto.setAadhaarPanDetailsDto(aadhaarPanDetailsDto);

        }
    }

    private void mapAuthorizedPersonDetailsDto(RegistrationEntity entity, RegistrationDto dto) {
        if (entity.getAuthorizedPersonDetailsEntity() != null) {
            AuthorizedPersonDetailsDto authorizedPersonDetailsDto = modelMapper.map(entity.getAuthorizedPersonDetailsEntity(), AuthorizedPersonDetailsDto.class);
            authorizedPersonDetailsDto.setRefId(dto.getRegistrationId());
            dto.setAuthorizedPersonDetailsDto(authorizedPersonDetailsDto);
        }
    }

    private void mapCompanyTurnOverDetailsDto(RegistrationEntity entity, RegistrationDto dto) {
        if (entity.getCompanyTurnOverEntity() != null) {
            CompanyTurnOverDto companyTurnOverDto = modelMapper.map(entity.getCompanyTurnOverEntity(), CompanyTurnOverDto.class);
            companyTurnOverDto.setRefId(dto.getRegistrationId());
            dto.setCompanyTurnOverDto(companyTurnOverDto);
        }
    }

    private void mapGstValidationsDto(RegistrationEntity entity, RegistrationDto dto) {
        if (entity.getGstValidationsEntity() != null) {
            GSTValidationsDto gstValidationsDto = modelMapper.map(entity.getGstValidationsEntity(), GSTValidationsDto.class);
            gstValidationsDto.setRefId(dto.getRegistrationId());
            dto.setGstValidationsDto(gstValidationsDto);
        }
    }

    private void mapLocationDetailsDto(RegistrationEntity entity, RegistrationDto dto) {
        if (entity.getLocationDetailsEntity() != null) {
            LocationDetailsDto locationDetailsDto = modelMapper.map(entity.getLocationDetailsEntity(), LocationDetailsDto.class);
            locationDetailsDto.setRefId(dto.getRegistrationId());
            dto.setLocationDetailsDto(locationDetailsDto);
        }
    }

    private void mapLoginCredentialsDto(RegistrationEntity entity, RegistrationDto dto) {
        if (entity.getLoginCredentialsEntity() != null) {
            LoginCredentialsDto loginCredentialsDto = modelMapper.map(entity.getLoginCredentialsEntity(), LoginCredentialsDto.class);
            loginCredentialsDto.setRefId(dto.getRegistrationId());
            dto.setLoginCredentialsDto(loginCredentialsDto);
        }
    }

    private void mapOrganizationDetailsDto(RegistrationEntity entity, RegistrationDto dto) {
        if (entity.getOrganizationDetailsEntity() != null) {
            OrganizationDetailsDto organizationDetailsDto = modelMapper.map(entity.getOrganizationDetailsEntity(), OrganizationDetailsDto.class);

            switch (entity.getOrganizationDetailsEntity().getOrganizationType()) {
                case PARTNERSHIP -> {
                    if (entity.getOrganizationDetailsEntity().getPartnershipEntity() != null) {
                        PartnerShipDto partnershipDto = modelMapper.map(entity.getOrganizationDetailsEntity().getPartnershipEntity(), PartnerShipDto.class);
                        organizationDetailsDto.setPartnership(partnershipDto);
                    }
                }
                case PRIVATE_LIMITED -> {
                    if (entity.getOrganizationDetailsEntity().getPrivateLimitedEntity() != null) {
                        PrivateLimitedDto privateLimitedDto = modelMapper.map(entity.getOrganizationDetailsEntity().getPrivateLimitedEntity(), PrivateLimitedDto.class);
                        organizationDetailsDto.setPrivateLimited(privateLimitedDto);
                    }
                }
                case SOLO_PROPRIETORSHIP -> {
                    if (entity.getOrganizationDetailsEntity().getProprietorshipEntity() != null) {
                        ProprietorShipDto proprietorshipDto = modelMapper.map(entity.getOrganizationDetailsEntity().getProprietorshipEntity(), ProprietorShipDto.class);
                        organizationDetailsDto.setProprietorship(proprietorshipDto);
                    }
                }
            }
            organizationDetailsDto.setRefId(dto.getRegistrationId());
            dto.setOrganizationDetailsDto(organizationDetailsDto);
        }
    }
}
