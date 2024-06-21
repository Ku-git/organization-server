package com.organization.service;

import com.organization.model.Organization;
import com.organization.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationService {

    private final OrganizationRepository repository;

    public OrganizationService(OrganizationRepository repository) {
        this.repository = repository;
    }

    public Organization findById(String organizationId) {
        Optional<Organization> opt = repository.findById(organizationId);
        return (opt.isPresent()) ? opt.get() : null;
    }

    public Organization create(Organization organization){
        organization.setOrganizationId(UUID.randomUUID().toString());
        organization = repository.save(organization);
        return organization;
    }
}
