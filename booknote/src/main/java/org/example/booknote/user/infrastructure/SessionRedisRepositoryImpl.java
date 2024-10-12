package org.example.booknote.user.infrastructure;

import lombok.RequiredArgsConstructor;
import org.example.booknote.user.service.port.SessionRedisRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class SessionRedisRepositoryImpl implements SessionRedisRepository {
    private final StringRedisTemplate redisTemplate;
    private static final String SEARCH_PREFIX = "cache:search:";

    @Override
    public String getToken(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void setToken(String key, String value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(SEARCH_PREFIX+key, value, timeout, unit);
    }
}
