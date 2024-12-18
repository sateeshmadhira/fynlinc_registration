package com.ess.registration.infrastructure.controller;
import com.ess.registration.core.constants.RegistrationConstants;
import com.ess.registration.core.exception.DuplicateException;
import com.ess.registration.core.req.RegistrationRequest;
import com.ess.registration.core.resp.ApiResponse;
import com.ess.registration.infrastructure.domain.sql.service.impl.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RegistrationConstants.BASE_URL)
@CrossOrigin("*")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<ApiResponse> createRegistration(@RequestBody @Valid RegistrationRequest requestDto) {
        try {
            ApiResponse apiResponse = registrationService.createRegistration(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse); // Use 201 CREATED for successful creation
        } catch (DuplicateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(), null)); // 409 CONFLICT for duplicates
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null)); // 500 INTERNAL SERVER ERROR for other issues
        }
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
    public ResponseEntity<ApiResponse> deleteRegistration(@PathVariable("ID") Long id) {
        ApiResponse response = registrationService.deleteRegistration(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping(RegistrationConstants.UPDATE)
    public ResponseEntity<ApiResponse> updateRegistration(@PathVariable("ID") Long id, @RequestBody @Valid RegistrationRequest requestDto) {
        ApiResponse response = registrationService.updateRegistration(id, requestDto);
        return ResponseEntity.ok(response);
    }
}

