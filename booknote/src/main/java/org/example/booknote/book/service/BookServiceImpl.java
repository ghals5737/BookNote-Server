package org.example.booknote.book.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.booknote.book.controller.port.BookService;
import org.example.booknote.book.domain.Book;
import org.example.booknote.book.domain.BookCreate;
import org.example.booknote.book.domain.BookUpdate;
import org.example.booknote.book.service.port.BookRepository;
import org.example.booknote.common.domain.exception.ResourceNotFoundException;
import org.example.booknote.common.service.port.ClockHolder;
import org.example.booknote.user.domain.User;
import org.example.booknote.user.service.port.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ClockHolder clockHolder;

    @Override
    public Book getBookById(long id) {
        return bookRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Books",id));
    }

    @Override
    public Book create(BookCreate bookCreate) {
        User user=userRepository.getById(bookCreate.userId());
        Book book=Book.from(user,bookCreate,clockHolder);
        return bookRepository.save(book);
    }

    @Override
    public Book update(long id,BookUpdate bookUpdate) {
        Book book= getBookById(id);
        book=book.update(bookUpdate,clockHolder);
        return bookRepository.save(book);
    }

    @Override
    public Book delete(long id) {
        Book book= getBookById(id);
        book=book.delete(clockHolder);
        return bookRepository.delete(book);
    }

    @Override
    public List<Book> getBooksByUserId(long userId) {
        return bookRepository.findByUserId(userId);
    }
}
