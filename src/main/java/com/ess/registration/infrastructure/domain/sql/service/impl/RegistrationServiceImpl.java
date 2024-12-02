package com.ess.registration.infrastructure.domain.sql.service.impl;

import com.ess.registration.core.dto.RegistrationDto;
import com.ess.registration.infrastructure.domain.sql.model.RegistrationEntity;
import com.ess.registration.infrastructure.domain.sql.repository.RegistrationRepository;
import com.ess.registration.infrastructure.domain.sql.service.handler.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository repository;

    @Autowired
    private Mapper mapper;

    @Override
    public RegistrationDto saveRegistration(RegistrationDto registrationDto) {
       RegistrationEntity registrationEntity= mapper.toEntity(registrationDto);
       RegistrationEntity savedEntity= repository.save(registrationEntity);
       return mapper.toDto(savedEntity);
    }
}
