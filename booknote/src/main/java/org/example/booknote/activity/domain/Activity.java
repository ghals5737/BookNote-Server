package org.example.booknote.activity.domain;

import org.example.booknote.common.service.port.ClockHolder;

import java.time.LocalDateTime;

public record Activity(
        Long id,
        String code,
        Long userId,
        Long bookId,
        Long memoId,
        LocalDateTime timestamp,
        String description
) {
    public static Activity from(ActivityCreate activityCreate, ClockHolder clockHolder){
        ActionType actionType=ActionType.valueOf(activityCreate.action());
        return new Activity(
                null,
                actionType.getLogCode(),
                activityCreate.userId(),
                activityCreate.bookId(),
                activityCreate.memoId(),
                clockHolder.now(),
                actionType.getLogMessage()
        );
    }
}
