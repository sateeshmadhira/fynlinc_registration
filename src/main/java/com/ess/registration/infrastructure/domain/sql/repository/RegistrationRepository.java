package com.ess.registration.infrastructure.domain.sql.repository;

import com.ess.registration.infrastructure.domain.sql.model.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity,Long> {

    // Custom query to find an entity by Aadhaar number, Pan number, or Gst number
    Optional<RegistrationEntity> findByAadhaarNumberOrPanNumberOrGstNumber(Long aadhaarNumber, String panNumber, String gstNumber);

}
