package org.example.booknote.user.domain;

import org.example.booknote.common.service.port.ClockHolder;
import org.example.booknote.common.service.port.PasswordEncoder;

import java.time.LocalDateTime;

public record User(
        Long id,
        String email,
        String name,
        String picture,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {
    public static User from(UserCreate userCreate, ClockHolder clockHolder){
        return new User(
                null,
                userCreate.email(),
                userCreate.name(),
                userCreate.picture(),
                clockHolder.now(),
                null
        );
    }


}
