package org.example.booknote.book.domain;

import lombok.Builder;
import lombok.Getter;
import org.example.booknote.common.service.port.ClockHolder;

import java.time.LocalDateTime;

@Getter
public class Book {
    private final Long id;
    private final String title;
    private final String author;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    @Builder
    public Book(Long id, String title, String author, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public static Book from(BookCreate bookCreate, ClockHolder clockHolder) {
        return Book.builder()
                .title(bookCreate.getTitle())
                .author(bookCreate.getAuthor())
                .createAt(clockHolder.now())
                .build();
    }

    public Book update(BookUpdate bookUpdate, ClockHolder clockHolder) {
        return Book.builder()
                .id(id)
                .title(bookUpdate.getTitle())
                .author(author)
                .createAt(createAt)
                .updateAt(clockHolder.now())
                .build();
    }
}
