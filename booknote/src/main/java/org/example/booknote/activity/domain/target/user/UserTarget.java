package org.example.booknote.activity.domain.target.user;

import org.example.booknote.activity.domain.target.Target;

public record UserTarget(
        int id,
        String email,
        String name,
        String picture
) implements Target {}

