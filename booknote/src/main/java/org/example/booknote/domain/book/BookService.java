package org.example.booknote.domain.book;

import lombok.RequiredArgsConstructor;
import org.example.booknote.storage.book.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    public List<Book> getBooksByUserId(Long userId){
        return bookRepository.findBooksByUserId(userId);
    }
}
