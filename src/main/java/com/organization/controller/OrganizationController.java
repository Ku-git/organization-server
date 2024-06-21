package com.organization.controller;

import com.organization.model.Organization;
import com.organization.service.OrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/{organizationId}")
    public ResponseEntity<Organization> getOrganization(@PathVariable("organizationId") String organizationId) {

        Organization organization = organizationService.findById(organizationId);
        return ResponseEntity.ok(organization);
    }

    @PostMapping
    public ResponseEntity<Organization> addOrganization(@RequestBody Organization request) {

        Organization organization = organizationService.create(request);
        return ResponseEntity.ok(organization);
    }
}
