package org.example.booknote.activity.domain;

public record ActivityCreate(
        String action,
        long userId,
        long bookId,
        long memoId
) {
}
