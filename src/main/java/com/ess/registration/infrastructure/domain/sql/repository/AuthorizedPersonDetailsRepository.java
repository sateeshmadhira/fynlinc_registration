package com.ess.registration.infrastructure.domain.sql.repository;

import com.ess.registration.infrastructure.domain.sql.model.AuthorizedPersonDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizedPersonDetailsRepository extends JpaRepository<AuthorizedPersonDetailsEntity,Long> {

    // Derived query method to find the most recent OTP based on phone number and expiry date
    Optional<AuthorizedPersonDetailsEntity> findTopByMobileNumberOrderByExpiryDateDesc(String mobileNumber);

}
