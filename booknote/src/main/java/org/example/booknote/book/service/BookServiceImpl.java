package org.example.booknote.book.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.booknote.book.controller.port.BookService;
import org.example.booknote.book.domain.Book;
import org.example.booknote.book.service.port.BookRepository;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Book getBookById(Long id) {
        return null;
    }

    @Override
    public Book create(Book book) {
        return null;
    }

    @Override
    public Book update(Book book) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
