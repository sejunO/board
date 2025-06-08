package com.sejun.board.jpa.user;

import com.sejun.board.domain.User.SignUpUser;
import com.sejun.board.domain.User.User;
import com.sejun.board.domain.User.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public boolean existsByName(String name) {
        return userJpaRepository.existsByName(name);
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
        List<UserEntity> all = userJpaRepository.findAll();
        
        all.forEach(user -> {
            System.out.println(user.getEmail());
            System.out.println(user.getName());
            System.out.println(user.getNick());
            System.out.println(user.getCreatedAt());
        });
        System.out.println(all.get(0).getName());
        UserEntity findUser = userJpaRepository.findByName(username)
                .orElseThrow(() -> {
                    throw new IllegalStateException("not found user with username = " + username);
                });
        return findUser.toUser();
    }
}
