package com.sejun.board.security;

import com.sejun.board.domain.User.User;
import com.sejun.board.domain.User.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserPrincipalService implements UserDetailsService {
    
    private final UserRepository userRepository;
    
    public UserPrincipalService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User findUser = userRepository.findByEmail(username);
        return new UserPrincipal(findUser.getId(), findUser.getName(), findUser.getPassword(), new ArrayList<>());
    }
}
