package com.sejun.board.domain.User;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserProcessor userProcessor;
    private final UserValidator userValidator;

    public UserService(UserProcessor userProcessor, UserValidator userValidator) {
        this.userProcessor = userProcessor;
        this.userValidator = userValidator;
    }

    public Long create(SignUpUser signUpUser) {
        userValidator.validateEmailNotExists(signUpUser.getEmail());
        return userProcessor.save(signUpUser);
    }

}
