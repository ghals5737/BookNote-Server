package org.example.booknote.memo.controller.port;

import org.example.booknote.memo.domain.Memo;
import org.example.booknote.memo.domain.MemoCreate;
import org.example.booknote.memo.domain.MemoUpdate;

import java.util.List;

public interface MemoService {
    Memo getMemoById(long id);
    Memo create(MemoCreate memoCreate);
    Memo update(long id,MemoUpdate memoUpdate);
    void delete(long id);
    List<Memo> getMemosByBookId(long bookId);
}
