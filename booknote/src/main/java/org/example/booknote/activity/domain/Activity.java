package org.example.booknote.activity.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.booknote.activity.domain.ActivityDeserializer;
import org.example.booknote.activity.domain.Actor;
import org.example.booknote.activity.domain.target.Target;
import org.example.booknote.common.service.port.ClockHolder;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@JsonDeserialize(using = ActivityDeserializer.class)
public record Activity(
        String id,
        String action,
        Actor actor,
        Target target,
        long timestamp
) {}

