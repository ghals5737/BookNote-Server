package org.example.booknote.book.domain;

import org.example.booknote.common.service.port.ClockHolder;
import org.example.booknote.user.domain.User;

import java.time.LocalDateTime;

public record Book(
        Long id,
        String title,
        String author,
        User user,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {

    public static Book from(User user,BookCreate bookCreate, ClockHolder clockHolder) {
        return new Book(
                null,
                bookCreate.title(),
                bookCreate.author(),
                user,
                clockHolder.now(),
                null
        );
    }

    public Book update(BookUpdate bookUpdate, ClockHolder clockHolder) {
        return new Book(
                this.id,
                bookUpdate.title(),
                this.author,
                this.user,
                this.createAt,
                clockHolder.now()
        );
    }
}
