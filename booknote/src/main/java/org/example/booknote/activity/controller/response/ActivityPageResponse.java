package org.example.booknote.activity.controller.response;

import org.example.booknote.activity.domain.Activity;

import java.util.List;

public record ActivityPageResponse (
        List<Activity> data,
        long total,
        long page,
        boolean hasNext
) {
}
