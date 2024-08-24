package org.example.booknote.memo.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.example.booknote.book.domain.Book;
import org.example.booknote.common.domain.exception.ResourceNotFoundException;
import org.example.booknote.memo.controller.response.MemoResponse;
import org.example.booknote.memo.domain.Memo;
import org.example.booknote.memo.domain.MemoUpdate;
import org.example.booknote.mock.TestClockHolder;
import org.example.booknote.mock.TestController;
import org.example.booknote.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class MemoControllerTest {

    @Test
    void 사용자는_memoId로_단건_조회할수있다(){
        //given
        TestController testController=TestController.builder().build();
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
                "test1",
                user1,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        Memo memo1=new Memo(
                1L,
                book1,
                "memo1",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        testController.userRepository.save(user1);
        testController.bookRepository.save(book1);
        testController.memoRepository.save(memo1);
        //when
        ResponseEntity<MemoResponse> result=testController.memoController.getById(1);
        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().memo()).isEqualTo("memo1");
    }

    @Test
    void 사용자가_존재하지_않는_메모를_조회할경우_에러가난다(){
        //given
        TestController testController=TestController.builder().build();
        //when
        //then
        assertThatThrownBy(()->{
           testController.memoController.getById(1);
        }).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void 사용자는_메모를_수정할수있다(){
        //given
        TestController testController=TestController.builder()
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
                "test1",
                user1,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        Memo memo1=new Memo(
                1L,
                book1,
                "memo1",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        testController.userRepository.save(user1);
        testController.bookRepository.save(book1);
        testController.memoRepository.save(memo1);
        //when
        ResponseEntity<MemoResponse> result=testController.memoController.update(1,new MemoUpdate("update"));
        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().memo()).isEqualTo("update");
    }
}
