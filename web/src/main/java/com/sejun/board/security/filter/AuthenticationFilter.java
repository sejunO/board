package com.sejun.board.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sejun.board.security.FormLoginRequest;
import com.sejun.board.security.UserPrincipal;
import com.sejun.board.security.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private final JwtTokenProvider jwtTokenProvider;
    
    public AuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        setFilterProcessesUrl("/v1/auth/login");
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            FormLoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), FormLoginRequest.class);
            
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password(), new ArrayList<>())
            );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String username = ((UserPrincipal) authResult.getPrincipal()).getUsername();
        Collection<? extends GrantedAuthority> authorities = ((UserPrincipal) authResult.getPrincipal()).getAuthorities();
        
        String token = jwtTokenProvider.createToken(username, authorities);
        response.addHeader("Authorization", token);
    }
}
