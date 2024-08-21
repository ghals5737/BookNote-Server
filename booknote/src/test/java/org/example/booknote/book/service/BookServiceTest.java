package org.example.booknote.book.service;


import org.example.booknote.book.controller.port.BookService;
import org.example.booknote.mock.FakeBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookServiceTest {
    private BookServiceImpl bookService;

    @BeforeEach
    void init(){
        FakeBookRepository fakeBookRepository = new FakeBookRepository();
        bookService = BookServiceImpl.builder()
                .bookRepository(fakeBookRepository)
                .build();

    }

    @Test
    public void BookId로_존재하는_책을_조회한다() {

    }

    @Test
    public void BookCreateDto를_사용하여_책을생성한다(){

    }

    @Test
    public void BookUpdateDto를_사용하여_책을수정한다(){

    }

    @Test
    public void BookId로_존재하는책을삭제한다(){

    }

    @Test
    public void UserId로_존재하는_책목록을_조회한다(){

    }
}
