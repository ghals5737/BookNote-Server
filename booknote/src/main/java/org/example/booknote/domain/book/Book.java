package org.example.booknote.domain.book;

import java.time.LocalDateTime;

public record Book(
        Long id,
        String title,
        String author,
        LocalDateTime createAt) {
}
