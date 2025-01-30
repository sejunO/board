package com.sejun.board.controller.v1.request;

import com.sejun.board.domain.User.SignUpUser;

public class UserSignUpRequest {
    private String name;
    private String nick;
    private String email;

    public String getName() {
        return name;
    }

    public String getNick() {
        return nick;
    }

    public String getEmail() {
        return email;
    }

    public SignUpUser toSignUp() {
        return SignUpUser.builder()
                .name(name)
                .nick(nick)
                .email(email)
                .build();
    }
}
