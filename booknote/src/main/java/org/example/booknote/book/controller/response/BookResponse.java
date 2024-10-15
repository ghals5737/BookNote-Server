package org.example.booknote.book.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.example.booknote.book.domain.Book;
import org.example.booknote.user.controller.response.UserResponse;

import java.time.LocalDateTime;

public record BookResponse(
        Long id,
        String title,
        boolean isPinned,
        UserResponse user,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        LocalDateTime createAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        LocalDateTime updateAt
) {
    public static BookResponse from(Book book) {
        return new BookResponse(
                book.id(),
                book.title(),
                book.isPinned(),
                UserResponse.from(book.user()),
                book.createAt(),
                book.updateAt()
        );
    }
}
