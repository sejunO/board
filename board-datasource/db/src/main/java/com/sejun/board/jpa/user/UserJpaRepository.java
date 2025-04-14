package com.sejun.board.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    
    Optional<UserEntity> findByName(String username);
    
    Optional<UserEntity> findByEmail(String email);
}
