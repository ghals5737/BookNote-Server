package org.example.booknote.book.service.port;

import org.example.booknote.book.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Book getById(long id);
    Optional<Book> findById(long id);
    List<Book> findByUserIdAndIsPinned(long userId,boolean isPinned);
    Book delete(Book book);
}
