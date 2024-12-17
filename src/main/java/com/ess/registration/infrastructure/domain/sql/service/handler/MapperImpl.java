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
            // Organization type-specific logic
            OrganizationType organizationType = registrationDto.getOrganizationType();
            switch (organizationType) {
                case PARTNERSHIP -> {
                    if ((registrationDto.getPartnership() != null)) {
                        PartnershipEntity partnershipEntity = modelMapper.map(registrationDto.getPartnership(), PartnershipEntity.class);
                        partnershipEntity.setRegistrationEntity(registrationEntity);
                        registrationEntity.setPartnershipEntity(partnershipEntity);
                        registrationEntity.setPrivateLimitedEntity(null);
                        registrationEntity.setProprietorshipEntity(null);
                    }
                }
                case PRIVATE_LIMITED -> {
                    if (registrationDto.getPrivateLimited() != null) {
                        PrivateLimitedEntity privateLimitedEntity = modelMapper.map(registrationDto.getPrivateLimited(), PrivateLimitedEntity.class);
                        privateLimitedEntity.setRegistrationEntity(registrationEntity);
                        registrationEntity.setPrivateLimitedEntity(privateLimitedEntity);
                        registrationEntity.setPartnershipEntity(null);
                        registrationEntity.setProprietorshipEntity(null);
                    }
                }
                case PROPRIETORSHIP -> {
                    if (registrationDto.getProprietorship() != null) {
                        ProprietorshipEntity proprietorshipEntity = modelMapper.map(registrationDto.getProprietorship(), ProprietorshipEntity.class);
                        proprietorshipEntity.setRegistrationEntity(registrationEntity);
                        registrationEntity.setProprietorshipEntity(proprietorshipEntity);
                        registrationEntity.setPartnershipEntity(null);
                        registrationEntity.setPrivateLimitedEntity(null);
                    }
                }
            }
        return registrationEntity;
    }

    @Override
    public RegistrationDto toDto(RegistrationEntity registrationEntity) {
        RegistrationDto registrationDto = modelMapper.map(registrationEntity, RegistrationDto.class);
            OrganizationType organizationType = registrationEntity.getOrganizationType();
            switch (organizationType) {
                case PARTNERSHIP -> {
                    if (registrationEntity.getPartnershipEntity() != null) {
                        PartnerShipDto partnershipDto = modelMapper.map(registrationEntity.getPartnershipEntity(), PartnerShipDto.class);
                        partnershipDto.setRefId(registrationDto.getRegistrationId());
                        registrationDto.setPartnership(partnershipDto);
                        registrationDto.setProprietorship(null);
                        registrationDto.setPrivateLimited(null);
                    }
                }
                case PRIVATE_LIMITED -> {
                    if (registrationEntity.getPrivateLimitedEntity() != null) {
                        PrivateLimitedDto privateLimitedDto = modelMapper.map(registrationEntity.getPrivateLimitedEntity(), PrivateLimitedDto.class);
                        privateLimitedDto.setRefId(registrationDto.getRegistrationId());
                        registrationDto.setPrivateLimited(privateLimitedDto);
                        registrationDto.setPartnership(null);
                        registrationDto.setProprietorship(null);
                    }
                }
                case PROPRIETORSHIP -> {
                    if (registrationEntity.getProprietorshipEntity() != null) {
                        ProprietorShipDto proprietorshipDto = modelMapper.map(registrationEntity.getProprietorshipEntity(), ProprietorShipDto.class);
                        proprietorshipDto.setRefId(registrationDto.getRegistrationId());
                        registrationDto.setProprietorship(proprietorshipDto);
                        registrationDto.setPartnership(null);
                        registrationDto.setPrivateLimited(null);
                    }
                }
            }
        return registrationDto;
    }
}
