package org.example.booknote.common.service.port;

import java.time.Clock;
import java.time.LocalDateTime;

public interface ClockHolder {
    LocalDateTime now();
}
