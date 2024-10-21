package org.example.booknote.book.service.port;

import java.util.Map;
import java.util.Set;

public interface BookRedisRepository {
    void cacheBookOrderChange(long userId, long bookId, int newOrder);
    Map<Object, Object> getCachedBookOrders(long userId);
    void clearCachedBookOrders(long userId);
    Set<String> getKeys();
}
