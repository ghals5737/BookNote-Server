package org.example.booknote.memo.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.booknote.book.domain.Book;
import org.example.booknote.book.service.port.BookRepository;
import org.example.booknote.common.domain.exception.ResourceNotFoundException;
import org.example.booknote.common.service.port.ClockHolder;
import org.example.booknote.memo.controller.port.MemoService;
import org.example.booknote.memo.domain.Memo;
import org.example.booknote.memo.domain.MemoCreate;
import org.example.booknote.memo.domain.MemoUpdate;
import org.example.booknote.memo.service.port.MemoRepository;
import org.example.booknote.user.service.port.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {
    private final MemoRepository memoRepository;
    private final BookRepository bookRepository;
    private final ClockHolder clockHolder;


    @Override
    public Memo getMemoById(long id) {
        return memoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Memos",id));
    }

    @Override
    public Memo create(MemoCreate memoCreate) {
        Book book=bookRepository.getById(memoCreate.bookId());
        Memo memo=Memo.from(book,memoCreate,clockHolder);
        return memoRepository.save(memo);
    }

    @Override
    public Memo update(long id, MemoUpdate memoUpdate) {
        Memo memo=getMemoById(id);
        memo=memo.update(memoUpdate,clockHolder);
        return memoRepository.save(memo);
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Memo> getMemosByBookId(long bookId) {
        return memoRepository.findByBookId(bookId);
    }
}
