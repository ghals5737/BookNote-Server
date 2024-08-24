package org.example.booknote.memo.infrastructure;

import lombok.RequiredArgsConstructor;
import org.example.booknote.book.service.port.BookRepository;
import org.example.booknote.memo.domain.Memo;
import org.example.booknote.memo.service.port.MemoRepository;
import org.example.booknote.user.service.port.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemoRepositoryImpl implements MemoRepository {
    private final MemoJpaRepository memoJpaRepository;


    @Override
    public Memo save(Memo memo) {
        return memoJpaRepository.save(MemoEntitiy.from(memo)).toModel();
    }

    @Override
    public Optional<Memo> findById(long id) {
        return memoJpaRepository.findById(id).map(MemoEntitiy::toModel);
    }

    @Override
    public List<Memo> findByBookId(long bookId) {
        return memoJpaRepository.findByBook_Id(bookId).stream().map(MemoEntitiy::toModel).toList();
    }

    @Override
    public void deleteById(long id) {

    }
}
