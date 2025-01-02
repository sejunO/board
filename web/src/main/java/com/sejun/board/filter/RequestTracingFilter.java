package com.sejun.board.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class RequestTracingFilter implements Filter {

    private static final String REQUEST_ID_HEADER = "X-Request-ID";
    private static final String USER_ID_HEADER = "X-User-ID";
    private static final String MDC_REQUEST_ID_KEY = "requestId";
    private static final String MDC_USER_ID_KEY = "userId";

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;

            // add to MDC
            MDC.put(MDC_REQUEST_ID_KEY, extractHeader(httpRequest, REQUEST_ID_HEADER).orElse(generateRequestId()));
            // fixme
            MDC.put(MDC_USER_ID_KEY, extractHeader(httpRequest, USER_ID_HEADER).orElse("GUEST"));

            try {
                chain.doFilter(request, response);
            } finally {
                MDC.clear();
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}

    private Optional<String> extractHeader(HttpServletRequest request, String headerKey) {
        return Optional.ofNullable(request.getHeader(headerKey))
                .filter(headerValue -> !headerValue.isEmpty());
    }

    private String generateRequestId() {
        return UUID.randomUUID().toString();
    }

}