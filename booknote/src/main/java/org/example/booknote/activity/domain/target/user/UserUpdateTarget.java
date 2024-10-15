package org.example.booknote.activity.domain.target.user;

import org.example.booknote.activity.domain.target.Target;

public record UserUpdateTarget(
        UserTarget before,
        UserTarget after
) implements Target {}