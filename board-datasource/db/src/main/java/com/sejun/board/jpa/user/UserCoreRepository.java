package com.sejun.board.jpa.User;

import com.sejun.board.domain.User.SignUpUser;
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
                .email(signUpUser.getEmail())
                .nick(signUpUser.getNick())
                .build());

        return saveUser.getId();
    }
}
