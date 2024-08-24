package org.example.booknote.memo.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoJpaRepository extends JpaRepository<MemoEntitiy,Long> {
    List<MemoEntitiy> findByBook_Id(long bookId);
}
