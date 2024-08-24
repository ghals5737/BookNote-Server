package org.example.booknote.user.controller.response;

import lombok.Builder;
import lombok.Getter;
import org.example.booknote.user.domain.User;

public record UserResponse(Long id,String email,String username) {
    public static UserResponse from(User user) {
        return new UserResponse(user.id(), user.email(), user.email());
    }
}
