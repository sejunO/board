package com.sejun.board.domain.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserProcessor userProcessor;
    private final UserValidator userValidator;
    
    private final PasswordEncoder passwordEncoder;
    
    public UserService(UserProcessor userProcessor, UserValidator userValidator, PasswordEncoder passwordEncoder) {
        this.userProcessor = userProcessor;
        this.userValidator = userValidator;
        this.passwordEncoder = passwordEncoder;
    }
    
    public Long create(SignUpUser signUpUser) {
        userValidator.validateSignUp(signUpUser.getEmail(), signUpUser.getName());
        signUpUser.setPassword(passwordEncoder.encode(signUpUser.getPassword()));
        return userProcessor.save(signUpUser);
    }
    
}
