package org.example.booknote.activity.domain;

import java.time.LocalDateTime;

public record Activity(
        Long id,
        String action,
        Long user_id,
        Long book_id,
        Long memo_id,
        String description,
        LocalDateTime timestamp
) {
}
