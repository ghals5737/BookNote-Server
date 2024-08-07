package org.example.booknote.storage.book;

import lombok.RequiredArgsConstructor;
import org.example.booknote.domain.book.Book;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {
    private BookJpaRepository bookJpaRepository;
    private UserBookJpaRepository userBookJpaRepository;

    public List<Book> findBooksByUserId(Long userId){
       return userBookJpaRepository.findBooksByUserId(userId).stream()
               .sorted(Comparator.comparing(BookEntity::getCreateAt).reversed())
               .map(BookEntity::toBook)
               .toList();
    }
}
