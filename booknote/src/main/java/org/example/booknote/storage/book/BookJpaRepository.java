package org.example.booknote.storage.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<BookEntity,Long> {
}
