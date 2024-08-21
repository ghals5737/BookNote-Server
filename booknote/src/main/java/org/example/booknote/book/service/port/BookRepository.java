package org.example.booknote.book.service.port;

import org.example.booknote.book.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(Long id);
    void deleteById(Long id);
}
