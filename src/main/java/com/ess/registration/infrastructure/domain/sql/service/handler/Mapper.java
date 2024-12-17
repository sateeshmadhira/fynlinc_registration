package com.ess.registration.infrastructure.domain.sql.service.handler;

import com.ess.registration.core.dto.RegistrationDto;
import com.ess.registration.infrastructure.domain.sql.model.RegistrationEntity;
import org.springframework.stereotype.Component;

@Component
public interface Mapper {

    // Mapping methods for DTOs and entities
    RegistrationEntity toEntity(RegistrationDto registrationDto);
    RegistrationDto toDto(RegistrationEntity registrationEntity);

}
