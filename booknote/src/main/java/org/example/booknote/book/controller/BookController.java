package org.example.booknote.book.controller;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.booknote.book.controller.port.BookService;
import org.example.booknote.book.controller.response.BookResponse;
import org.example.booknote.book.domain.BookCreate;
import org.example.booknote.book.domain.BookUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Builder
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable long id) {
        return ResponseEntity
                .ok()
                .body(BookResponse.from(bookService.getBookById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(@PathVariable long id, @RequestBody BookUpdate bookUpdate){
        return ResponseEntity
                .ok()
                .body(BookResponse.from(bookService.update(id,bookUpdate)));
    }

    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody BookCreate bookCreate){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BookResponse.from(bookService.create(bookCreate)));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<BookResponse>> getByUserId(@PathVariable long id,@RequestParam boolean isPinned){
        return ResponseEntity
                .ok()
                .body(bookService.getBooksByUserIdAndIsPinned(id,isPinned).stream().map(BookResponse::from).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> delete(@PathVariable long id){
        return ResponseEntity
                .ok()
                .body(BookResponse.from(bookService.delete(id)));
    }
}
