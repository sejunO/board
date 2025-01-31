package com.sejun.board.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * RequestWrapperFilter
 *
 * HttpServletRequest를  CustomHttpRequestWrapper로 감싼 후 chain을 진행한다.
 *
 */
@Component
public class RequestWrapperFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpRequest) {
            chain.doFilter(new CustomHttpRequestWrapper(httpRequest), response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
