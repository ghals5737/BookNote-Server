package org.example.booknote.book.service;

    import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.example.booknote.book.domain.Book;
import org.example.booknote.book.domain.BookCreate;
import org.example.booknote.book.domain.BookUpdate;
import org.example.booknote.mock.FakeBookRepository;
import org.example.booknote.mock.FakeUserRepository;
import org.example.booknote.mock.TestClockHolder;
import org.example.booknote.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class BookServiceTest {
    private BookServiceImpl bookService;

    @BeforeEach
    void init(){
        FakeBookRepository fakeBookRepository = new FakeBookRepository();
        FakeUserRepository fakeUserRepository = new FakeUserRepository();
        bookService = BookServiceImpl.builder()
                .bookRepository(fakeBookRepository)
                .userRepository(fakeUserRepository)
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
        User user2=new User(
                2L,
                "test2@naver.com",
                "user2",
                "user2",
                LocalDateTime.now()
                ,LocalDateTime.now()
        );
        Book book1=new Book(
                1L,
                "book1",
                "test1",
                user1,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        Book book2=new Book(
                2L,
                "book2",
                "test2",
                user1,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        Book book3=new Book(
                3L,
                "book3",
                "test3",
                user2,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        fakeUserRepository.save(user1);
        fakeUserRepository.save(user2);
        fakeBookRepository.save(book1);
        fakeBookRepository.save(book2);
        fakeBookRepository.save(book3);
    }

    @Test
    public void BookId로_존재하는_책을_조회한다() {
        //given
        //when
        Book result=bookService.getBookById(1);
        //then
        assertThat(result.title()).isEqualTo("book1");
        assertThat(result.author()).isEqualTo("test1");
    }

    @Test
    public void UserId로_존재하는_책목록을_조회한다(){
        //given
        //when
        List<Book> results=bookService.getBooksByUserId(1);
        //then
        assertThat(results.size()).isEqualTo(2);
        assertThat(results.get(0).id()).isEqualTo(1L);
        assertThat(results.get(0).title()).isEqualTo("book1");
        assertThat(results.get(0).author()).isEqualTo("test1");
        assertThat(results.get(1).id()).isEqualTo(2L);
        assertThat(results.get(1).title()).isEqualTo("book2");
        assertThat(results.get(1).author()).isEqualTo("test2");
    }

    @Test
    public void BookCreateDto를_사용하여_책을생성한다(){
        //given
        BookCreate bookCreate=new BookCreate(1,"test5","aaa");

        //when
        Book result=bookService.create(bookCreate);

        //then
        assertThat(result.title()).isEqualTo("test5");
        assertThat(result.author()).isEqualTo("aaa");
    }

    @Test
    public void BookUpdateDto를_사용하여_책을수정한다(){
        //given
        BookUpdate bookUpdate=new BookUpdate("update");

        //when
        Book result=bookService.update(1,bookUpdate);

        //then
        assertThat(result.title()).isEqualTo("update");
    }

    @Test
    public void BookId로_존재하는책을삭제한다(){
        //given
        //when
        //then
    }
}
