package com.organization.utils;

import org.springframework.util.Assert;

public class UserContextHolder {

    private static final ThreadLocal<UserContext> threadLocal = new ThreadLocal<>();

    public static final UserContext getUserContext() {
        UserContext userContext = threadLocal.get();

        if(userContext == null) {
            userContext = createEmptyContext();
            threadLocal.set(userContext);
        }
        return threadLocal.get();
    }

    public static final void setContext(UserContext userContext) {
        Assert.notNull(userContext, "Only non-null UserContext instances are permitted");
        threadLocal.set(userContext);
    }

    private static UserContext createEmptyContext() {
        return new UserContext();
    }
}
