package org.example.booknote.memo.controller.response;

import org.example.booknote.book.controller.response.BookResponse;
import org.example.booknote.memo.domain.Memo;

import java.time.LocalDateTime;

public record MemoResponse(
        Long id,
        BookResponse book,
        String title,
        String content,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {
    public static MemoResponse from(Memo memo){
        return new MemoResponse(
                memo.id(),
                BookResponse.from(memo.book()),
                memo.title(),
                memo.content(),
                memo.createAt(),
                memo.updateAt()
        );
    }
}
