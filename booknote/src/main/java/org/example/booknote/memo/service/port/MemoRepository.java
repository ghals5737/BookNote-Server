package org.example.booknote.memo.service.port;

import org.example.booknote.memo.domain.Memo;

import java.util.List;
import java.util.Optional;

public interface MemoRepository {
    Memo save(Memo memo);
    Optional<Memo> findById(long id);
    List<Memo> findByBookId(long bookId);
    void deleteById(long id);
}
