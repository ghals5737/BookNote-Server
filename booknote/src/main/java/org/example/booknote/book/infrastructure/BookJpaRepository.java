package org.example.booknote.book.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<BookEntity,Long> {
}
