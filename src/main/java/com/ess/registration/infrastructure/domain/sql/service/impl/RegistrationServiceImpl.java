package com.ess.registration.infrastructure.domain.sql.service.impl;
import com.ess.registration.core.dto.RegistrationDto;
import com.ess.registration.core.exception.DuplicateException;
import com.ess.registration.core.exception.InvalidOrganizationTypeException;
import com.ess.registration.core.req.RegistrationRequest;
import com.ess.registration.core.resp.ApiResponse;
import com.ess.registration.infrastructure.domain.sql.model.RegistrationEntity;
import com.ess.registration.infrastructure.domain.sql.repository.RegistrationRepository;
import com.ess.registration.infrastructure.domain.sql.service.handler.Mapper;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static com.ess.registration.infrastructure.domain.sql.service.handler.ServiceHandlers.*;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private Mapper mapper;

    @Override
    @Transactional
    public ApiResponse createRegistration(@Valid RegistrationRequest requestDto) {
        try {
            // Convert DTO to entity
            RegistrationEntity registrationEntity = mapper.toEntity(requestDto.getRegistration());

            // Check for duplicate Aadhaar, PAN, or GST numbers
            Optional<RegistrationEntity> existingEntity = registrationRepository.findByAadhaarNumberOrPanNumberOrGstNumber(
                    registrationEntity.getAadhaarNumber(),
                    registrationEntity.getPanNumber(),
                    registrationEntity.getGstNumber()
            );

            // Handle duplicates with meaningful exceptions
            if (existingEntity.isPresent()) {
                RegistrationEntity entity = existingEntity.get();
                if (entity.getAadhaarNumber().equals(registrationEntity.getAadhaarNumber())) {
                    throw new DuplicateException("Aadhaar number already exists: " + registrationEntity.getAadhaarNumber());
                }
                if (entity.getPanNumber().equals(registrationEntity.getPanNumber())) {
                    throw new DuplicateException("PAN number already exists: " + registrationEntity.getPanNumber());
                }
                if (entity.getGstNumber().equals(registrationEntity.getGstNumber())) {
                    throw new DuplicateException("GST number already exists: " + registrationEntity.getGstNumber());
                }
            }

            // Validate organization type
            validateOrganizationType(registrationEntity);

            // Save the entity
            RegistrationEntity savedEntity = registrationRepository.save(registrationEntity);

            // Convert saved entity to DTO for response
            RegistrationDto registrationDto = mapper.toDto(savedEntity);

            // Return success response
            return new ApiResponse("Successfully Created Registration", registrationDto);
        } catch (DuplicateException e) {
            // Rethrow DuplicateException to be handled at the controller level
            throw e;
        } catch (Exception e) {
            // Log and throw other exceptions as internal server errors
            throw new RuntimeException("Error occurred while creating registration", e);
        }
    }


    @Override
    @Transactional
    public ApiResponse getRegistrationById(Long id) {
        RegistrationEntity registrationEntity = registrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registration not found for ID: " + id));
        RegistrationDto registrationDto = mapper.toDto(registrationEntity);
        return new ApiResponse("Registration found for ID:", registrationDto);
    }

    @Override
    @Transactional
    public ApiResponse getAllRegistrations() {
        List<RegistrationEntity> entities = registrationRepository.findAll();
        List<RegistrationDto> dtos = entities.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return new ApiResponse("Registrations found:", dtos);
    }

    @Override
    @Transactional
    public ApiResponse deleteRegistration(Long id) {
        RegistrationEntity registrationEntity = registrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registration not found for ID: " + id));
        registrationEntity.setDelFlag(0);
        RegistrationEntity savedEntity = registrationRepository.save(registrationEntity);
        RegistrationDto savedDto = mapper.toDto(savedEntity);
        return new ApiResponse("Registration found for ID Is Soft Deleted Successfully : ", savedDto);
    }

    @Override
    @Transactional
    public ApiResponse updateRegistration(Long id, RegistrationRequest requestDto) {
        // Fetch the existing entity
        RegistrationEntity existingEntity = registrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registration not found for ID: " + id));

        // Get the RegistrationDto from the request
        RegistrationDto registrationDto = requestDto.getRegistration();

        // Update common fields
        updateCommonFields(existingEntity, registrationDto);

        // Handle organization details based on organizationType
        if (registrationDto.getOrganizationType() != null) {
            existingEntity.setOrganizationType(registrationDto.getOrganizationType());
            validateOrganizationType(existingEntity); // Validate based on the new organization type

            // Update child DTO fields based on organization type
            switch (registrationDto.getOrganizationType()) {
                case PARTNERSHIP:
                    if (registrationDto.getPartnership() != null) {
                        updatePartnershipDetails(existingEntity.getPartnershipEntity(), registrationDto.getPartnership());
                    }
                    break;
                case PRIVATE_LIMITED:
                    if (registrationDto.getPrivateLimited() != null) {
                        updatePrivateLimitedDetails(existingEntity.getPrivateLimitedEntity(), registrationDto.getPrivateLimited());
                    }
                    break;
                case PROPRIETORSHIP:
                    if (registrationDto.getProprietorship() != null) {
                        updateProprietorshipDetails(existingEntity.getProprietorshipEntity(), registrationDto.getProprietorship());
                    }
                    break;
                default:
                    throw new InvalidOrganizationTypeException("Unsupported organization type: " + registrationDto.getOrganizationType());
            }
        }

        // Save the updated entity
        RegistrationEntity updatedEntity = registrationRepository.save(existingEntity);

        // Convert the updated entity back to DTO
        RegistrationDto updatedDto = mapper.toDto(updatedEntity);

        return new ApiResponse("Successfully Updated Registration", updatedDto);
    }
}
