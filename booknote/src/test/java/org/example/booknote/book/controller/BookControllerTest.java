package org.example.booknote.book.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.example.booknote.book.controller.response.BookResponse;
import org.example.booknote.book.domain.Book;
import org.example.booknote.book.domain.BookUpdate;
import org.example.booknote.common.domain.exception.ResourceNotFoundException;
import org.example.booknote.mock.TestClockHolder;
import org.example.booknote.mock.TestController;
import org.example.booknote.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class BookControllerTest {

    @Test
    void 사용자는_bookId로_단건_조회할수있다() {
        //given
        TestController testController=TestController.builder().build();
        User user=new User(
                1L,
                "test1@naver.com",
                "user1",
                "user1",
                LocalDateTime.now()
                ,LocalDateTime.now()
        );
        testController.userRepository.save(user);
        testController.bookRepository.save(new Book(
                1L,
                "book1",
                "test1",
                user,
                LocalDateTime.now(),
                LocalDateTime.now()
        ));
        //when
        ResponseEntity<BookResponse> result=testController.bookController.getById(1);
        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().title()).isEqualTo("book1");
        assertThat(result.getBody().author()).isEqualTo("test1");
    }

    @Test
    void 사용자가_존재하지_않는_책을_조회할경우_에러가난다(){
        //given
        TestController testController=TestController.builder().build();
        //when
        //then
        assertThatThrownBy(()->{
            testController.bookController.getById(1);
        }).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void 사용자는_책을_수정할수있다(){
        //given
        TestController testController=TestController.builder()
                .clockHolder(new TestClockHolder())
                .build();
        User user=new User(
                1L,
                "test1@naver.com",
                "user1",
                "user1",
                LocalDateTime.now()
                ,LocalDateTime.now()
        );
        testController.userRepository.save(user);
        testController.bookRepository.save(new Book(
                1L,
                "book1",
                "test1",
                user,
                LocalDateTime.now(),
                LocalDateTime.now()
        ));
        //when
        ResponseEntity<BookResponse> result=testController.bookController.update(1,new BookUpdate("update"));
        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().title()).isEqualTo("update");
    }
}