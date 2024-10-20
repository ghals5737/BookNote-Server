package org.example.booknote.user.domain;

public record Token(
        String access_token,
        String refresh_token
) {
}
