package com.sejun.board.security.config;

import com.sejun.board.security.filter.AuthenticationFilter;
import com.sejun.board.security.filter.JwtAuthorizationFilter;
import com.sejun.board.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationConfiguration authenticationConfiguration;
    
    public SecurityConfig(JwtTokenProvider jwtTokenProvider, AuthenticationConfiguration authenticationConfiguration) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationConfiguration = authenticationConfiguration;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    
    @Bean
    public AuthenticationFilter authenticationFilter() throws Exception {
        AuthenticationFilter filter = new AuthenticationFilter(jwtTokenProvider);
        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        return filter;
    }
    
    @Bean
    public SecurityFilterChain boardSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/v1/boards/**")
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/v1/boards").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(new JwtAuthorizationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                // JWT 인증 필터 추가
                .addFilterBefore(new JwtAuthorizationFilter(jwtTokenProvider), AuthenticationFilter.class)
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // 기본 HTTP Basic 인증 비활성화 (원하는 경우 제거 가능)
                .formLogin(form -> form.disable())
                .httpBasic(Customizer.withDefaults());
        
        return http.build();
    }
}
