package com.ess.registration.infrastructure.controller;

import com.ess.registration.core.dto.RegistrationDto;
import com.ess.registration.infrastructure.domain.sql.service.impl.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/registrations")
public class RegistrationController {
    // Define your API endpoints here
    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<RegistrationDto> saveRegistration(@RequestBody RegistrationDto registrationDto){
        RegistrationDto saveDto = registrationService.saveRegistration(registrationDto);
        return ResponseEntity.ok(saveDto);
    }
}
