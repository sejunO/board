package com.sejun.board.support;

import com.sejun.board.domain.User.User;

public class ApiUser {
    public ApiUser(Long id) {
        this.id = id;
    }
    
    private Long id;
    
    public User toUser() {
        return User.builder().id(id).build();
    }
}
