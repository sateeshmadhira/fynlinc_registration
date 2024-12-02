package com.ess.registration.infrastructure.domain.sql.repository;

import com.ess.registration.infrastructure.domain.sql.model.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity,Long> {

}
