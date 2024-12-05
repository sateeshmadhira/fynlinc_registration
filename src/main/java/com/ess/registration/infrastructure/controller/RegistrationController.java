package com.ess.registration.infrastructure.controller;
import com.ess.registration.core.constants.RegistrationConstants;
import com.ess.registration.core.req.RegistrationRequest;
import com.ess.registration.core.resp.ApiResponse;
import com.ess.registration.infrastructure.domain.sql.service.impl.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RegistrationConstants.BASE_URL)
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<ApiResponse> createRegistration(@RequestBody RegistrationRequest requestDto) {
        ApiResponse apiResponse = registrationService.createRegistration(requestDto);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping(RegistrationConstants.BY_ID)
    public ResponseEntity<ApiResponse> getRegistrationById(@PathVariable("ID") Long id) {
        ApiResponse apiResponse = registrationService.getRegistrationById(id);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllRegistrations() {
        ApiResponse apiResponse = registrationService.getAllRegistrations();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(RegistrationConstants.BY_ID)
    public ResponseEntity<ApiResponse> deleteRegistration(@PathVariable ("ID") Long id) {
        ApiResponse response=registrationService.deleteRegistration(id);
        return ResponseEntity.ok(response);
    }
}
