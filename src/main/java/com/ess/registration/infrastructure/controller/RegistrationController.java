package com.ess.registration.infrastructure.controller;

import com.ess.registration.core.dto.RegistrationDto;
import com.ess.registration.core.exception.RegistrationNotFoundException;
import com.ess.registration.infrastructure.domain.sql.service.impl.RegistrationServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationServiceImpl registrationService;

    @PostMapping
    public ResponseEntity<RegistrationDto> createRegistration(@RequestBody RegistrationDto registrationDto) {
        RegistrationDto createdRegistration = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(createdRegistration, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getRegistrationById(@PathVariable Long id) {
        RegistrationDto registration = registrationService.getRegistrationById(id);
        return ResponseEntity.ok(registration);
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations() {
        List<RegistrationDto> registrations = registrationService.getAllRegistrations();
        return ResponseEntity.ok(registrations);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        registrationService.deleteRegistration(id);
        return ResponseEntity.noContent().build();
    }
}
