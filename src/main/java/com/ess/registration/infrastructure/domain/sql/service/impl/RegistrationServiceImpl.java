package com.ess.registration.infrastructure.domain.sql.service.impl;
import com.ess.registration.core.dto.RegistrationDto;
import com.ess.registration.core.req.RegistrationRequest;
import com.ess.registration.core.resp.ApiResponse;
import com.ess.registration.infrastructure.domain.sql.model.RegistrationEntity;
import com.ess.registration.infrastructure.domain.sql.repository.RegistrationRepository;
import com.ess.registration.infrastructure.domain.sql.service.handler.Mapper;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private Mapper mapper;

    public ApiResponse createRegistration(RegistrationRequest requestDto) {
        RegistrationEntity registrationEntity = mapper.toEntity(requestDto.getRegistrationDto());
        RegistrationEntity savedEntity = registrationRepository.save(registrationEntity);
        RegistrationDto registrationDto =  mapper.toDto(savedEntity);
        return new ApiResponse("Success", registrationDto);
    }

    public ApiResponse getRegistrationById(Long id) {
        RegistrationEntity registrationEntity = registrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registration not found for ID: " + id));
        RegistrationDto registrationDto =  mapper.toDto(registrationEntity);
        return new ApiResponse("Success", registrationDto);
    }

    public ApiResponse getAllRegistrations() {
        List<RegistrationEntity> entities = registrationRepository.findAll();
        List<RegistrationDto> dtos= entities.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return new ApiResponse("Success", dtos);
    }

    public ApiResponse deleteRegistration(Long id) {
        RegistrationEntity registrationEntity =registrationRepository.findById(id).get();
        registrationEntity.setDelFlag(0);
        RegistrationEntity savedEntity = registrationRepository.save(registrationEntity);
        RegistrationDto savedDto=mapper.toDto(savedEntity);
        return new ApiResponse("Soft Deleted",savedDto);

    }
}
