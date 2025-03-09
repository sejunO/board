package com.sejun.board.domain.User;

import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateEmailNotExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("This email is already use");
        }
    }
}
