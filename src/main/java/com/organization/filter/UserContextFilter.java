package com.organization.filter;


import com.organization.utils.UserContext;
import com.organization.utils.UserContextHolder;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 需要@Component來讓spring做auto scan並註冊在其中，若沒有的話會無法使用。
 * 若是非spring框架下，servlet下使用filter並不需要特別註冊的方式使用
 */
@Component
public class UserContextFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(UserContextFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        UserContextHolder.getUserContext().setCorrelationId(request.getHeader(UserContext.CORRELATION_ID));
        UserContextHolder.getUserContext().setAuthToken(request.getHeader(UserContext.AUTH_TOKEN));
        UserContextHolder.getUserContext().setUserId(request.getHeader(UserContext.USER_ID));
        UserContextHolder.getUserContext().setOrganizationId(request.getHeader(UserContext.ORGANIZATION_ID));

        logger.debug("UserContextFilter Correlation Id: {}", UserContextHolder.getUserContext().getCorrelationId());

        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
