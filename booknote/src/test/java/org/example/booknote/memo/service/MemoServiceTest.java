package org.example.booknote.memo.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.example.booknote.book.domain.Book;
import org.example.booknote.book.domain.BookCreate;
import org.example.booknote.book.domain.BookUpdate;
import org.example.booknote.memo.domain.Memo;
import org.example.booknote.memo.domain.MemoCreate;
import org.example.booknote.memo.domain.MemoUpdate;
import org.example.booknote.mock.FakeBookRepository;
import org.example.booknote.mock.FakeMemoRepository;
import org.example.booknote.mock.TestClockHolder;
import org.example.booknote.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class MemoServiceTest {
    private MemoServiceImpl memoService;

    @BeforeEach
    void init(){
        FakeMemoRepository fakeMemoRepository= new FakeMemoRepository();
        FakeBookRepository fakeBookRepository= new FakeBookRepository();
        memoService = MemoServiceImpl.builder()
                .memoRepository(fakeMemoRepository)
                .bookRepository(fakeBookRepository)
                .clockHolder(new TestClockHolder())
                .build();
        User user1=new User(
                1L,
                "test1@naver.com",
                "user1",
                "user1",
                LocalDateTime.now()
                ,LocalDateTime.now()
        );
        Book book1=new Book(
                1L,
                "book1",
                user1,
                false,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        Memo memo1=new Memo(
                1L,
                book1,
                "title1",
                "memo1",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        Memo memo2=new Memo(
                2L,
                book1,
                "title2",
                "memo2",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        Memo memo3=new Memo(
                3L,
                book1,
                "title3",
                "memo3",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        Memo memo4=new Memo(
                4L,
                book1,
                "title4",
                "memo4",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        fakeBookRepository.save(book1);
        fakeMemoRepository.save(memo1);
        fakeMemoRepository.save(memo2);
        fakeMemoRepository.save(memo3);
        fakeMemoRepository.save(memo4);
    }

    @Test
    public void MemoId로_존재하는_메모를_조회한다() {
        //given
        //when
        Memo result=memoService.getMemoById(1);
        //then
        assertThat(result.content()).isEqualTo("memo1");
    }

    @Test
    public void BookId로_존재하는_메모목록을_조회한다(){
        //given
        //when
        List<Memo> results=memoService.getMemosByBookId(1L);

        //then
        assertThat(results.size()).isEqualTo(4);
        assertThat(results.get(0).content()).isEqualTo("memo1");
        assertThat(results.get(1).content()).isEqualTo("memo2");
        assertThat(results.get(2).content()).isEqualTo("memo3");
        assertThat(results.get(3).content()).isEqualTo("memo4");
    }

    @Test
    public void MemoCreateDto를_사용하여_메모를_저장한다(){
        //given
        MemoCreate memoCreate=new MemoCreate(1,"title1","create");

        //when
        Memo result=memoService.create(memoCreate);

        //then
        assertThat(result.title()).isEqualTo("title1");
        assertThat(result.content()).isEqualTo("create");
    }

    @Test
    public void MemoUpdatseDto를_사용하여_메모를_수정한다(){
        //given
        MemoUpdate memoUpdate=new MemoUpdate("title","update");

        //when
        Memo result=memoService.update(1,memoUpdate);

        //then
        assertThat(result.title()).isEqualTo("title1");
        assertThat(result.content()).isEqualTo("update");
    }

    @Test
    public void MemoId로_존재하는_메모를_삭제한다(){
        //given
        //when
        //then
    }
}
