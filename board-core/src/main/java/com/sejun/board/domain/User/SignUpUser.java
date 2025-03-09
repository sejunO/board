package com.sejun.board.domain.User;

import lombok.Builder;

@Builder
public class SignUpUser {
    private String name;
    private String nick;
    private String email;

    private String password;

    public String getName() {
        return name;
    }

    public String getNick() {
        return nick;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
