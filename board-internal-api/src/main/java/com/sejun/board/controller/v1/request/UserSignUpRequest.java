package com.sejun.board.controller.v1.request;

import com.sejun.board.domain.User.SignUpUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserSignUpRequest(
        @NotBlank
        String name,
        @NotBlank
        String nick,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password) {
    public SignUpUser toSignUp() {
        return SignUpUser.builder()
                .name(name)
                .password(password)
                .nick(nick)
                .email(email)
                .build();
    }
}
