package com.organization.utils;

import lombok.Data;

@Data
public class UserContext {

    public static final String CORRELATION_ID = "txn-correlation-id";
    public static final String AUTH_TOKEN = "txn-auth-token";
    public static final String USER_ID = "txn-user-id";
    public static final String ORGANIZATION_ID = "txn-organization-id";

    private String correlationId;
    private String authToken;
    private String userId;
    private String organizationId;

}
