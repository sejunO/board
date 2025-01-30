package com.sejun.board.domain.User;

import org.springframework.stereotype.Component;

@Component
public class UserProcessor {
    private final UserValidator userValidator;
    private final UserRepository userRepository;

    public UserProcessor(UserValidator userValidator, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }
    public Long save(SignUpUser signUpUser) {
        userValidator.validateEmailNotExists(signUpUser.getEmail());
        return userRepository.save(signUpUser);
    }
}
