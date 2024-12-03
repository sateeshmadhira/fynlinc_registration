package com.ess.registration.infrastructure.domain.sql.service.impl;

import com.ess.registration.core.dto.RegistrationDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public interface RegistrationService {

   // RegistrationDto saveRegistration(RegistrationDto registrationDto );
    RegistrationDto saveRegistration(RegistrationDto registrationDto, Map<String, MultipartFile> files) throws Exception;
}
