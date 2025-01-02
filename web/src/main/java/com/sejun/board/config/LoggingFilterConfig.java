package com.sejun.board.config;

import com.sejun.board.filter.RequestTracingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingFilterConfig {

    @Bean
    public FilterRegistrationBean<RequestTracingFilter> loggingFilter() {
        FilterRegistrationBean<RequestTracingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestTracingFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
