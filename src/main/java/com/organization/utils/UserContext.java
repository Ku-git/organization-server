package com.organization.utils;


import org.springframework.http.HttpHeaders;

public class UserContext {

    public static final String CORRELATION_ID = "txn-correlation-id";
    public static final String AUTH_TOKEN = HttpHeaders.AUTHORIZATION;
    public static final String USER_ID = "txn-user-id";
    public static final String ORGANIZATION_ID = "txn-organization-id";

    private static final ThreadLocal<String> correlationId = new ThreadLocal<>();
    private static final ThreadLocal<String> authToken = new ThreadLocal<>();
    private static final ThreadLocal<String> userId = new ThreadLocal<>();
    private static final ThreadLocal<String> organizationId = new ThreadLocal<>();

    public static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(CORRELATION_ID, getCorrelationId());

        return headers;
    }

    public static String getCorrelationId() {
        return correlationId.get();
    }

    public static void setCorrelationId(String cid) {
        correlationId.set(cid);
    }

    public static String getAuthToken() {
        return authToken.get();
    }

    public static void setAuthToken(String token) {
        authToken.set(token);
    }

    public static String getUserId() {
        return userId.get();
    }

    public static void setUserId(String id) {
        userId.set(id);
    }

    public static String getOrganizationId() {
        return organizationId.get();
    }

    public static void setOrganizationId(String id) {
        organizationId.set(id);
    }

}
