package com.sejun.board.domain.User;

import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    private final UserRepository userRepository;
    
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public void validateSignUp(String email, String username) {
        validateEmailNotExists(email);
        validateIdNotExists(username);
    }
    
    private void validateEmailNotExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("This email is already use");
        }
    }
    
    private void validateIdNotExists(String username) {
        if (userRepository.existsByName(username)) {
            throw new IllegalArgumentException("this username is already use");
        }
    }
}
