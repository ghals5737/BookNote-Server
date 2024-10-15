package org.example.booknote.book.infrastructure;

import lombok.RequiredArgsConstructor;
import org.example.booknote.book.domain.Book;
import org.example.booknote.book.service.port.BookRepository;
import org.example.booknote.common.domain.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final BookJpaRepository bookJpaRepository;

    @Override
    public Book save(Book book) {
        return bookJpaRepository.save(BookEntity.from(book)).toModel();
    }

    @Override
    public Book getById(long id) {
        return findById(id).orElseThrow(()->new ResourceNotFoundException("Books",id));
    }

    @Override
    public Optional<Book> findById(long id) {
        return bookJpaRepository.findById(id).map(BookEntity::toModel);
    }

    @Override
    public List<Book> findByUserIdAndIsPinned(long userId,boolean isPinned) {
        return bookJpaRepository.findByUser_IdAndIsDeletedAndIsPinned(userId,false,isPinned).stream().map(BookEntity::toModel).toList();
    }

    @Override
    public Book delete(Book book) {
        return  bookJpaRepository.save(BookEntity.fromDelete(book)).toModel();
    }
}
