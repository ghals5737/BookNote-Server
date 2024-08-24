package org.example.booknote.book.controller.response;

import lombok.Builder;
import lombok.Getter;
import org.example.booknote.book.domain.Book;
import org.example.booknote.user.controller.response.UserResponse;

import java.time.LocalDateTime;

public record BookResponse(
        Long id,
        String title,
        String author,
        UserResponse user,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {
    public static BookResponse from(Book book) {
        return new BookResponse(
                book.id(),
                book.title(),
                book.author(),
                UserResponse.from(book.user()),
                book.createAt(),
                book.updateAt()
        );
    }
}
