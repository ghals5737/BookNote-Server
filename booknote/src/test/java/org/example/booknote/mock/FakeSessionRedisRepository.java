package org.example.booknote.mock;

import org.example.booknote.user.service.port.SessionRedisRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FakeSessionRedisRepository implements SessionRedisRepository {
    private final Map<String, RedisEntry> store = Collections.synchronizedMap(new HashMap<>());
    private record RedisEntry(String value, long expirationTime) {
        public boolean isExpired() {
            return System.currentTimeMillis() > expirationTime;
        }
    }

    @Override
    public String getToken(String key) {
        RedisEntry entry = store.get(key);
        if (entry == null || entry.isExpired()) {
            store.remove(key); // 만료된 항목 삭제
            return null;
        }
        return entry.value();
    }

    @Override
    public void setToken(String key, String value, long timeout, TimeUnit unit) {
        long expirationTime = System.currentTimeMillis() + timeout;
        store.put(key, new RedisEntry(value, expirationTime));
    }
}
