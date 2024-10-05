package org.example.booknote.activity.domain;

import java.time.LocalDateTime;

public record Activity(
        Long id,
        ActionType action,
        Long user_id,
        Long book_id,
        Long memo_id,
        LocalDateTime timestamp
) {
}
