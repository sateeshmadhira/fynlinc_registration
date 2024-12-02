package com.ess.registration.infrastructure.domain.sql.service.impl;

import com.ess.registration.core.dto.RegistrationDto;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {

    RegistrationDto saveRegistration(RegistrationDto registration);
}
