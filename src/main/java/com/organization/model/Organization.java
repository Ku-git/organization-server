package com.organization.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Organization {

    @Id
    String organizationId;

    String name;

    String contactName;

    String contactEmail;

    String contactPhone;

}
