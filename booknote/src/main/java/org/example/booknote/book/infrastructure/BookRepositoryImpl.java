package org.example.booknote.book.infrastructure;

import lombok.RequiredArgsConstructor;
import org.example.booknote.book.domain.Book;
import org.example.booknote.book.service.port.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final BookJpaRepository jpaRepository;

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
