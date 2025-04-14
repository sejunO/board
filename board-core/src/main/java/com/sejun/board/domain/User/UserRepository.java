package com.sejun.board.domain.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    boolean existsByEmail(String email);
    
    Long save(SignUpUser signUpUser);
    
    User findByUsername(String username);
    
    User findByEmail(String email);
}
