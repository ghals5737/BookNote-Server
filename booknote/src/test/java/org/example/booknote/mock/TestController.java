package org.example.booknote.mock;

import lombok.Builder;
import org.example.booknote.book.controller.BookController;
import org.example.booknote.book.controller.port.BookService;
import org.example.booknote.book.service.BookServiceImpl;
import org.example.booknote.book.service.port.BookRepository;
import org.example.booknote.common.service.port.ClockHolder;
import org.example.booknote.memo.controller.MemoController;
import org.example.booknote.memo.controller.port.MemoService;
import org.example.booknote.memo.domain.Memo;
import org.example.booknote.memo.service.MemoServiceImpl;
import org.example.booknote.memo.service.port.MemoRepository;
import org.example.booknote.user.service.port.UserRepository;

public class TestController {
    public final BookRepository bookRepository;
    public final BookService bookService;
    public final UserRepository userRepository;
    public final MemoRepository memoRepository;
    public final MemoService memoService;
    public final BookController bookController;
    public final MemoController memoController;

    @Builder
    public TestController(ClockHolder clockHolder){
        this.bookRepository=new FakeBookRepository();
        this.userRepository=new FakeUserRepository();
        this.memoRepository=new FakeMemoRepository();

        this.bookService=BookServiceImpl.builder()
                .bookRepository(this.bookRepository)
                .userRepository(this.userRepository)
                .clockHolder(clockHolder)
                .build();
        this.memoService= MemoServiceImpl.builder()
                .memoRepository(memoRepository)
                .bookRepository(bookRepository)
                .clockHolder(clockHolder)
                .build();

        this.bookController=BookController.builder()
                .bookService(this.bookService)
                .build();

        this.memoController= MemoController.builder()
                .memoService(this.memoService)
                .build();
    }
}
