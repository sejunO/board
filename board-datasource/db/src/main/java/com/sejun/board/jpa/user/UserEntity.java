package com.sejun.board.jpa.user;

import com.sejun.board.domain.User.User;
import com.sejun.board.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "user")
public class UserEntity extends BaseEntity {
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
    
    public User toUser() {
        return User.builder()
                .id(getId())
                .name(name)
                .password(password)
                .build();
    }
}
