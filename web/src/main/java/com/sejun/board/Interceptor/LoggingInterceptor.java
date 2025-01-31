package com.sejun.board.Interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sejun.board.filter.CustomHttpRequestWrapper;
import com.sejun.board.filter.CustomHttpResponseWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            logRequest(request);
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (handler instanceof HandlerMethod) {
            logResponse(request, response);
        }
    }

    private Map<String, String> getRequestParams(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            paramMap.put(paramName, request.getParameter(paramName));
        }

        return paramMap;
    }

    private void logRequest(HttpServletRequest request) {
        log.info("Request [{} {}]", request.getMethod(), request.getRequestURI());

        if (request instanceof CustomHttpRequestWrapper) {
            CustomHttpRequestWrapper requestWrapper = (CustomHttpRequestWrapper) request;
            String requestBody = new String(requestWrapper.getRequestBody());

            // Request Body
            if (!requestBody.isEmpty()) {
                log.info("Request Body: \n{}", requestBody);
            }
        }

        // Request Param
        if (request.getParameterNames().hasMoreElements()) {
            log.info("Request Params: [{}]", getRequestParams(request));
        }
    }

    private void logResponse(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        log.info("Response [{} {} {}]", request.getMethod(), request.getRequestURI(), response.getStatus());

        if (response instanceof CustomHttpResponseWrapper responseWrapper) {
            byte[] responseData = responseWrapper.getResponseData();
            if (responseData != null && responseData.length > 0) {
                ObjectMapper mapper = new ObjectMapper();
                Object responseDataJson = mapper.readValue(new String(responseData), Object.class);
                String responseBody = mapper.writeValueAsString(responseDataJson);

                log.info("Response Body: [{}]", responseBody);
            } else {
                log.info("Response Body: [Empty]");
            }
        }
    }
}
