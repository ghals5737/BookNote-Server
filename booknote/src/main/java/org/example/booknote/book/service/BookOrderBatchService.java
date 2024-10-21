package org.example.booknote.book.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.booknote.book.domain.Book;
import org.example.booknote.book.domain.BookOrderChange;
import org.example.booknote.book.service.port.BookRedisRepository;
import org.example.booknote.book.service.port.BookRepository;
import org.example.booknote.common.service.port.ClockHolder;
import org.example.booknote.user.service.port.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@Builder
@RequiredArgsConstructor
public class BookOrderBatchService {
    private final BookRepository bookRepository;
    private final BookRedisRepository bookRedisRepository;
    private final ClockHolder clockHolder;

    @Scheduled(fixedRate = 30000)
    public void updateBookOrdersFromCache() {
        Set<String> keys = bookRedisRepository.getKeys();

        if (keys != null && !keys.isEmpty()) {
            for (String key : keys) {
                String userId = key.split(":")[2];
                Map<Object, Object> cachedBookOrders = bookRedisRepository.getCachedBookOrders(Long.parseLong(userId));

                if (!cachedBookOrders.isEmpty()) {
                    cachedBookOrders.forEach((bookId, newOrder) -> {
                        Book book = bookRepository.findById(Long.parseLong((String) bookId)).orElseThrow();
                        Book updatedBook = new Book(
                                book.id(),
                                book.title(),
                                book.user(),
                                book.isPinned(),
                                book.image(),
                                Integer.parseInt((String)newOrder),
                                book.createAt(),
                                clockHolder.now()
                        );
                        bookRepository.save(updatedBook);
                    });

                    bookRedisRepository.clearCachedBookOrders(Long.parseLong(userId));
                }
            }
        }
    }
}