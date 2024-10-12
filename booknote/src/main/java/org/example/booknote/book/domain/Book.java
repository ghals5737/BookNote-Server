package org.example.booknote.book.domain;

import org.example.booknote.common.service.port.ClockHolder;
import org.example.booknote.user.domain.User;

import java.time.LocalDateTime;

public record Book(
        Long id,
        String title,
        User user,
        boolean isPinned,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {

    public static Book from(User user,BookCreate bookCreate, ClockHolder clockHolder) {
        return new Book(
                null,
                bookCreate.title(),
                user,
                bookCreate.isPinned(),
                clockHolder.now(),
                clockHolder.now()
        );
    }

    public Book update(BookUpdate bookUpdate, ClockHolder clockHolder) {
        return new Book(
                this.id,
                bookUpdate.title(),
                this.user,
                bookUpdate.isPinned(),
                this.createAt,
                clockHolder.now()
        );
    }

    public Book delete(ClockHolder clockHolder) {
        return new Book(
                this.id,
                this.title(),
                this.user,
                this.isPinned(),
                this.createAt,
                clockHolder.now()
        );
    }
}
