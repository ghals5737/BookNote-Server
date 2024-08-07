package org.example.booknote.storage.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserBookJpaRepository extends JpaRepository<UserBookEntity,Long> {
    @Query("SELECT ub.bookEntity FROM UserBookEntity ub WHERE ub.userEntity.id = :userId")
    List<BookEntity> findBooksByUserId(@Param("userId") Long userId);
}
