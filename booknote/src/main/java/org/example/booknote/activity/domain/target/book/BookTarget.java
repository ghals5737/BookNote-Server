package org.example.booknote.activity.domain.target.book;

import org.example.booknote.activity.domain.target.Target;
import org.example.booknote.activity.domain.target.user.UserTarget;

import java.time.OffsetDateTime;

public record BookTarget(
        int id,
        String title,
        UserTarget user,
        boolean isPinned,
        String createAt,
        String updateAt
) implements Target {}

