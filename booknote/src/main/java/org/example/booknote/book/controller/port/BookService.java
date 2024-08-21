package org.example.booknote.book.controller.port;

import org.example.booknote.book.domain.Book;

public interface BookService {
    Book getBookById(Long id);
    Book create(Book book);
    Book update(Book book);
    void delete(Long id);
}
