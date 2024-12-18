package com.ess.registration.infrastructure.domain.sql.repository;

import com.ess.registration.infrastructure.domain.sql.model.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Long> {

    @Query("SELECT r FROM RegistrationEntity r WHERE " +
            "r.aadhaarNumber = :aadhaarNumber OR " +
            "r.panNumber = :panNumber OR " +
            "r.gstNumber = :gstNumber")
    Optional<RegistrationEntity> findByAadhaarNumberOrPanNumberOrGstNumber(
            @Param("aadhaarNumber") Long aadhaarNumber,
            @Param("panNumber") String panNumber,
            @Param("gstNumber") String gstNumber);
}

