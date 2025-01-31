package com.sejun.board.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ResponseWrapperFilter
 *
 * HttpServletResponse  CustomHttpResponseWrapper 감싼 후 chain을 진행한다.
 * 응답데이터가 있는 경우 출력한다. -> 클라이언트
 *
 */
public class ResponseWrapperFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (response instanceof HttpServletResponse httpServletResponse) {
            CustomHttpResponseWrapper responseWrapper = new CustomHttpResponseWrapper(httpServletResponse);
            chain.doFilter(request, responseWrapper);

            byte[] responseData = responseWrapper.getResponseData();
            if (responseData != null && responseData.length > 0) {
                response.getOutputStream().write(responseData);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
