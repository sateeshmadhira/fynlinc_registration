package com.ess.registration.infrastructure.domain.sql.service.handler;

import com.ess.registration.core.dto.PartnerShipDto;
import com.ess.registration.core.dto.PrivateLimitedDto;
import com.ess.registration.core.dto.ProprietorShipDto;
import com.ess.registration.core.dto.RegistrationDto;
import com.ess.registration.core.utils.OrganizationType;
import com.ess.registration.infrastructure.domain.sql.model.PartnershipEntity;
import com.ess.registration.infrastructure.domain.sql.model.PrivateLimitedEntity;
import com.ess.registration.infrastructure.domain.sql.model.ProprietorshipEntity;
import com.ess.registration.infrastructure.domain.sql.model.RegistrationEntity;

public class ServiceHandlers {

    // Method to update common fields
    public static void updateCommonFields(RegistrationEntity existingEntity, RegistrationDto registrationDto) {
        if (registrationDto.getOrganizationName() != null) {
            existingEntity.setOrganizationName(registrationDto.getOrganizationName());
        }
        if (registrationDto.getAadhaarNumber() != null) {
            existingEntity.setAadhaarNumber(registrationDto.getAadhaarNumber());
        }
        if (registrationDto.getPanNumber() != null) {
            existingEntity.setPanNumber(registrationDto.getPanNumber());
        }
        if (registrationDto.getCancelledCheque() != null) {
            existingEntity.setCancelledCheque(registrationDto.getCancelledCheque());
        }
        if (registrationDto.getTurnOver() != null) {
            existingEntity.setTurnOver(registrationDto.getTurnOver());
        }
        if (registrationDto.getGstNumber() != null) {
            existingEntity.setGstNumber(registrationDto.getGstNumber());
        }
        if (registrationDto.getCity() != null) {
            existingEntity.setCity(registrationDto.getCity());
        }
        if (registrationDto.getState() != null) {
            existingEntity.setState(registrationDto.getState());
        }
        if (registrationDto.getZipCode() != 0) {
            existingEntity.setZipCode(registrationDto.getZipCode());
        }
    }

    // Helper methods to update child DTO fields
    public static void updatePartnershipDetails(PartnershipEntity existingPartnership, PartnerShipDto updatedPartnership) {
        if (updatedPartnership.getAccountStatement() != null) {
            existingPartnership.setAccountStatement(updatedPartnership.getAccountStatement());
        }
        if (updatedPartnership.getITRS() != null) {
            existingPartnership.setITRS(updatedPartnership.getITRS());
        }
        if (updatedPartnership.getGSTReturns() != null) {
            existingPartnership.setGSTReturns(updatedPartnership.getGSTReturns());
        }
        if (updatedPartnership.getGSTCertificate() != null) {
            existingPartnership.setGSTCertificate(updatedPartnership.getGSTCertificate());
        }
        if (updatedPartnership.getCompanyPAN() != null) {
            existingPartnership.setCompanyPAN(updatedPartnership.getCompanyPAN());
        }
        if (updatedPartnership.getPartnershipDeed() != null) {
            existingPartnership.setPartnershipDeed(updatedPartnership.getPartnershipDeed());
        }
        if (updatedPartnership.getKYC() != null) {
            existingPartnership.setKYC(updatedPartnership.getKYC());
        }
    }

    public static void updatePrivateLimitedDetails(PrivateLimitedEntity existingPrivateLimited, PrivateLimitedDto updatedPrivateLimited) {
        if (updatedPrivateLimited.getAccountStatement() != null) {
            existingPrivateLimited.setAccountStatement(updatedPrivateLimited.getAccountStatement());
        }
        if (updatedPrivateLimited.getITRS() != null) {
            existingPrivateLimited.setITRS(updatedPrivateLimited.getITRS());
        }
        if (updatedPrivateLimited.getGSTReturns() != null) {
            existingPrivateLimited.setGSTReturns(updatedPrivateLimited.getGSTReturns());
        }
        if (updatedPrivateLimited.getGSTCertificate() != null) {
            existingPrivateLimited.setGSTCertificate(updatedPrivateLimited.getGSTCertificate());
        }
        if (updatedPrivateLimited.getCompanyPAN() != null) {
            existingPrivateLimited.setCompanyPAN(updatedPrivateLimited.getCompanyPAN());
        }
        if (updatedPrivateLimited.getCertificateOfIncorporation() != null) {
            existingPrivateLimited.setCertificateOfIncorporation(updatedPrivateLimited.getCertificateOfIncorporation());
        }
        if (updatedPrivateLimited.getAOA() != null) {
            existingPrivateLimited.setAOA(updatedPrivateLimited.getAOA());
        }
        if (updatedPrivateLimited.getMOA() != null) {
            existingPrivateLimited.setMOA(updatedPrivateLimited.getMOA());
        }
        if (updatedPrivateLimited.getLatestShareHoldingPattern() != null) {
            existingPrivateLimited.setLatestShareHoldingPattern(updatedPrivateLimited.getLatestShareHoldingPattern());
        }
        if (updatedPrivateLimited.getKYC() != null) {
            existingPrivateLimited.setKYC(updatedPrivateLimited.getKYC());
        }
    }

    public static void updateProprietorshipDetails(ProprietorshipEntity existingProprietorship, ProprietorShipDto updatedProprietorship) {
        if (updatedProprietorship.getAccountStatement() != null) {
            existingProprietorship.setAccountStatement(updatedProprietorship.getAccountStatement());
        }
        if (updatedProprietorship.getITRS() != null) {
            existingProprietorship.setITRS(updatedProprietorship.getITRS());
        }
        if (updatedProprietorship.getGSTReturns() != null) {
            existingProprietorship.setGSTReturns(updatedProprietorship.getGSTReturns());
        }
        if (updatedProprietorship.getGSTCertificate() != null) {
            existingProprietorship.setGSTCertificate(updatedProprietorship.getGSTCertificate());
        }
        if (updatedProprietorship.getKYC() != null) {
            existingProprietorship.setKYC(updatedProprietorship.getKYC());
        }
    }


    public static void validateOrganizationType(RegistrationEntity entity) {
        if (entity.getOrganizationType() == OrganizationType.PARTNERSHIP && entity.getPartnershipEntity() == null) {
            throw new IllegalStateException("PartnershipEntity is required for PARTNERSHIP type");
        }
        if (entity.getOrganizationType() == OrganizationType.PRIVATE_LIMITED && entity.getPrivateLimitedEntity() == null) {
            throw new IllegalStateException("PrivateLimitedEntity is required for PRIVATE_LIMITED type");
        }
        if (entity.getOrganizationType() == OrganizationType.PROPRIETORSHIP && entity.getProprietorshipEntity() == null) {
            throw new IllegalStateException("ProprietorshipEntity is required for PROPRIETORSHIP type");
        }
    }
}
