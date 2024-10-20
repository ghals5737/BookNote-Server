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
    private static final String ACCESS_TOKEN_PREFIX = "accesstoken:";
    private static final String REFRESH_TOKEN_PREFIX = "refreshtoken:";

    @Override
    public String getToken(String key) {
        return redisTemplate.opsForValue().get(ACCESS_TOKEN_PREFIX+key);
    }

    @Override
    public String getRefreshToken(String key) {
        return redisTemplate.opsForValue().get(REFRESH_TOKEN_PREFIX+key);
    }

    @Override
    public void setToken(String key, String value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(ACCESS_TOKEN_PREFIX+key, value, timeout, unit);
    }

    @Override
    public void setRefreshToken(String key, String value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(REFRESH_TOKEN_PREFIX+key, value, timeout, unit);
    }
}
