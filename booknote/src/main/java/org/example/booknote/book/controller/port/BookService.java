package org.example.booknote.book.controller.port;

import org.example.booknote.book.domain.Book;
import org.example.booknote.book.domain.BookCreate;
import org.example.booknote.book.domain.BookUpdate;

import java.util.List;

public interface BookService {
    Book getBookById(long id);
    Book create(BookCreate bookCreate);
    Book update(long id,BookUpdate bookUpdate);
    void delete(long id);
    List<Book> getBooksByUserId(long userId);
}