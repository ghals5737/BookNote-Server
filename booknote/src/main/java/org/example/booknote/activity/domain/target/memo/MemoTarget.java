package org.example.booknote.activity.domain.target.memo;

import org.example.booknote.activity.domain.target.Target;
import org.example.booknote.activity.domain.target.book.BookTarget;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public record MemoTarget(
        int id,
        BookTarget book,
        String title,
        String content,
        String createAt,
        String updateAt
) implements Target {}