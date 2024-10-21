package org.example.booknote.book.infrastructure;

import lombok.RequiredArgsConstructor;
import org.example.booknote.book.service.port.BookRedisRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class BookRedisRepositoryImpl implements BookRedisRepository {
    private final StringRedisTemplate redisTemplate;
    private final String BOOK_ORDER_PRIFIX="book:order:";

    public void cacheBookOrderChange(long userId, long bookId, int newOrder) {
        String key = BOOK_ORDER_PRIFIX + userId;
        redisTemplate.opsForHash().put(key, String.valueOf(bookId), String.valueOf(newOrder));
        redisTemplate.expire(key, 10, TimeUnit.MINUTES);
    }

    public Map<Object, Object> getCachedBookOrders(long userId) {
        String key = BOOK_ORDER_PRIFIX + userId;
        return redisTemplate.opsForHash().entries(key);
    }

    public void clearCachedBookOrders(long userId) {
        String key = BOOK_ORDER_PRIFIX + userId;
        redisTemplate.delete(key);
    }

    public Set<String> getKeys(){
        return redisTemplate.keys(BOOK_ORDER_PRIFIX+"*");
    }
}

