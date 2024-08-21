package org.example.booknote.book.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookJpaRepository extends JpaRepository<UserBookEntity,Long> {

}
