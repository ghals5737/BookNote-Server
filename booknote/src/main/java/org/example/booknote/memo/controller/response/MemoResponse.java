package org.example.booknote.memo.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.booknote.book.controller.response.BookResponse;
import org.example.booknote.memo.domain.Memo;

import java.time.LocalDateTime;

public record MemoResponse(
        Long id,
        BookResponse book,
        String title,
        String content,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        LocalDateTime createAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
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
