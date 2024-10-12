package org.example.booknote.memo.domain;

import org.example.booknote.book.domain.Book;
import org.example.booknote.common.service.port.ClockHolder;
import org.example.booknote.user.domain.User;

import java.time.LocalDateTime;

public record Memo(
        Long id,
        Book book,
        String title,
        String content,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {
    public static Memo from(Book book,MemoCreate memoCreate, ClockHolder clockHolder) {
        return new Memo(
                null,
                book,
                memoCreate.title(),
                memoCreate.content(),
                clockHolder.now(),
                clockHolder.now()
        );
    }

    public Memo update(MemoUpdate memoUpdate,ClockHolder clockHolder) {
        return new Memo(
                this.id,
                this.book,
                memoUpdate.title(),
                memoUpdate.content(),
                this.createAt,
                clockHolder.now()
        );
    }

    public Memo delete(ClockHolder clockHolder) {
        return new Memo(
                this.id,
                this.book,
                this.title,
                this.content,
                this.createAt,
                clockHolder.now()
        );
    }
}
