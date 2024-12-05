package com.ess.registration.infrastructure.domain.sql.repository;

import com.ess.registration.infrastructure.domain.sql.model.AuthorizedPersonDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizedPersonDetailsRepository extends JpaRepository<AuthorizedPersonDetailsEntity, Long> {

    /**
     * Fetch the most recent OTP record for a given phone number, ordered by expiry date in descending order.
     *
     * @param phoneNumber the phone number to search for.
     * @return the most recent OTP record for the given mobile number, if available.
     */
    Optional<AuthorizedPersonDetailsEntity> findTopByPhoneNumberOrderByExpiryDateDesc(String phoneNumber);
}
