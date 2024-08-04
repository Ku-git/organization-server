package com.organization.service;

import com.organization.event.process.KafkaProcessor;
import com.organization.model.Organization;
import com.organization.repository.OrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationService {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationService.class);

    private final OrganizationRepository repository;

    private final KafkaProcessor kafkaProcessor;

    public OrganizationService(OrganizationRepository repository, KafkaProcessor kafkaProcessor) {
        this.repository = repository;
        this.kafkaProcessor = kafkaProcessor;
    }

    public Organization findById(String organizationId) {
        Optional<Organization> opt = repository.findById(organizationId);
        return (opt.isPresent()) ? opt.get() : null;
    }

    public Organization create(Organization organization){

        organization.setOrganizationId(UUID.randomUUID().toString());
        organization = repository.save(organization);

        kafkaProcessor.process("CREATE", organization.getOrganizationId());

        return organization;
    }

    public void delete(String organizationId) {

        repository.deleteById(organizationId);

        kafkaProcessor.process("DELETE", organizationId);
    }

}
