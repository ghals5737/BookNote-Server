package org.example.booknote.book.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookJpaRepository extends JpaRepository<BookEntity,Long> {
    List<BookEntity> findByUser_IdAndIsDeletedAndIsPinned(long userId,boolean isDeleted,boolean isPinned);
}
