package com.sejun.board.jpa.user;

import com.sejun.board.domain.User.SignUpUser;
import com.sejun.board.domain.User.User;
import com.sejun.board.domain.User.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserCoreRepository implements UserRepository {
    
    private final UserJpaRepository userJpaRepository;
    
    public UserCoreRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }
    
    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }
    
    @Override
    public Long save(SignUpUser signUpUser) {
        UserEntity saveUser = userJpaRepository.save(UserEntity.builder()
                .name(signUpUser.getName())
                .password(signUpUser.getPassword())
                .email(signUpUser.getEmail())
                .nick(signUpUser.getNick())
                .build());
        
        return saveUser.getId();
    }
    
    @Override
    public User findByUsername(String username) {
        UserEntity findUser = userJpaRepository.findByName(username)
                .orElseThrow(() -> {
                    throw new IllegalStateException("not found user with username = " + username);
                });
        return findUser.toUser();
    }
    
    @Override
    public User findByEmail(String email) {
        UserEntity findUser = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new IllegalStateException("not found user with email = " + email);
                });
        return findUser.toUser();
    }
}
