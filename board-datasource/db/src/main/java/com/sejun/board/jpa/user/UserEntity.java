package com.sejun.board.jpa.User;

import com.sejun.board.jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserEntity extends BaseEntity {
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
