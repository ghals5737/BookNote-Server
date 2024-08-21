package org.example.booknote.common.infrastructure;

import org.example.booknote.common.service.port.ClockHolder;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class SystemClockHolder implements ClockHolder {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now(Clock.system(ZoneId.of("UTC")));
    }
}

