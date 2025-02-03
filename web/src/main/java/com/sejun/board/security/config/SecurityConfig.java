package com.sejun.board.security.config;

import com.sejun.board.security.filter.JwtAuthenticationFilter;
import com.sejun.board.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 비활성화 (JWT 사용 시 CSRF 보호가 필요하지 않음)
                .csrf(csrf -> csrf.disable())
                // 세션을 사용하지 않음 (stateless)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 엔드포인트별 접근 권한 설정
                .authorizeHttpRequests(authorize -> authorize
                        // 인증 관련 URL은 누구나 접근할 수 있도록
                        .requestMatchers("/v1/**").permitAll()
                        // 그 외의 모든 요청은 인증 필요
                        .anyRequest().authenticated()
                )
                // JWT 인증 필터 추가 (UsernamePasswordAuthenticationFilter보다 앞에 추가할 수 있음)
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                // 기본 HTTP Basic 인증 비활성화 (원하는 경우 제거 가능)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
