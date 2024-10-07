package org.example.booknote.activity.controller.response;

import org.example.booknote.activity.domain.Activity;

import java.time.LocalDateTime;

public record ActivityResponse(
        Long id,
        String code,
        Long userId,
        Long bookId,
        Long memoId,
        LocalDateTime timestamp,
        String description
) {
    public static ActivityResponse from(Activity activity){
        return new ActivityResponse(
                activity.id(),
                activity.code(),
                activity.userId(),
                activity.bookId(),
                activity.memoId(),
                activity.timestamp(),
                activity.description()
        );
    }
}
