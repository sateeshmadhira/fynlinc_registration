package com.ess.registration.infrastructure.domain.sql.service.impl;

import com.ess.registration.core.dto.RegistrationDto;
import com.ess.registration.core.exception.RegistrationNotFoundException;
import com.ess.registration.infrastructure.domain.sql.model.RegistrationEntity;
import com.ess.registration.infrastructure.domain.sql.repository.RegistrationRepository;
import com.ess.registration.infrastructure.domain.sql.service.handler.Mapper;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private Mapper mapper;

    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        RegistrationEntity registrationEntity = mapper.toEntity(registrationDto);
        RegistrationEntity savedEntity = registrationRepository.save(registrationEntity);
        return mapper.toDto(savedEntity);
    }

    public RegistrationDto getRegistrationById(Long id) {
        RegistrationEntity registrationEntity = registrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registration not found for ID: " + id));
        return mapper.toDto(registrationEntity);
    }

    public List<RegistrationDto> getAllRegistrations() {
        List<RegistrationEntity> entities = registrationRepository.findAll();
        return entities.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteRegistration(Long id) {
        if (!registrationRepository.existsById(id)) {
            throw new EntityNotFoundException("Registration not found for ID: " + id);
        }
        registrationRepository.deleteById(id);
    }
}
