package com.sejun.board.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    
    boolean existsByName(String name);
    
    Optional<UserEntity> findByName(String username);
}
