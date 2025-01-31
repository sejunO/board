package com.sejun.board.config;

import com.sejun.board.filter.RequestTracingFilter;
import com.sejun.board.filter.RequestWrapperFilter;
import com.sejun.board.filter.ResponseWrapperFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingFilterConfig {

    @Bean
    public FilterRegistrationBean<RequestTracingFilter> logConfigFilter() {
        FilterRegistrationBean<RequestTracingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestTracingFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<RequestWrapperFilter> requestWrapperFilterFilter() {
        FilterRegistrationBean<RequestWrapperFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestWrapperFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<ResponseWrapperFilter> responseWrapperFilterFilter() {
        FilterRegistrationBean<ResponseWrapperFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ResponseWrapperFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(3);
        return registrationBean;
    }
}
