package org.example.booknote.user.service.port;

import java.util.concurrent.TimeUnit;

public interface SessionRedisRepository {
    String getToken(String key);
    String getRefreshToken(String key);
    void setToken(String key, String value, long timeout, TimeUnit unit);
    void setRefreshToken(String key, String value, long timeout, TimeUnit unit);
}
