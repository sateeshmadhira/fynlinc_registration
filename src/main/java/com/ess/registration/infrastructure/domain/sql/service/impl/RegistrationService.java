package com.ess.registration.infrastructure.domain.sql.service.impl;

import com.ess.registration.core.req.RegistrationRequest;
import com.ess.registration.core.resp.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {
    ApiResponse createRegistration(RegistrationRequest requestDto);
    ApiResponse getRegistrationById(Long id);
    ApiResponse getAllRegistrations();
    ApiResponse deleteRegistration(Long id);
    ApiResponse updateRegistration(Long id, RegistrationRequest requestDto);
}
