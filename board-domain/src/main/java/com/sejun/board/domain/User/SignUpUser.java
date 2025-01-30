package com.sejun.board.domain.User;

import lombok.Builder;

@Builder
public class SignUpUser {
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
}
