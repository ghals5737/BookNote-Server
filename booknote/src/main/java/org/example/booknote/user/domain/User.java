package org.example.booknote.user.domain;

import org.example.booknote.common.service.port.ClockHolder;

import java.time.LocalDateTime;

public record User(Long id, String email, String username, String password, LocalDateTime createAt,
                   LocalDateTime updateAt) {
    public static User from(UserCreate userCreate, ClockHolder clockHolder){
        return new User(
                null,
                userCreate.email(),
                userCreate.username(),
                userCreate.password(),
                clockHolder.now(),
                null
        );
    }


}
