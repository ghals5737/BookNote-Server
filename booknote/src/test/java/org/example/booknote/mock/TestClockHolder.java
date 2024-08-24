package org.example.booknote.mock;

import org.example.booknote.common.service.port.ClockHolder;

import java.time.LocalDateTime;

public class TestClockHolder implements ClockHolder {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.parse("2024-08-22T10:15:30");
    }
}
