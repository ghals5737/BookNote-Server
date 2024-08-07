package org.example.booknote.controller;

import lombok.RequiredArgsConstructor;
import org.example.booknote.dto.book.BookCreateRequest;
import org.example.booknote.dto.book.BookResponse;
import org.example.booknote.dto.book.BookUpdateRequest;
import org.example.booknote.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/books")
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public BookResponse getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/books")
    public BookResponse createBook(@RequestBody BookCreateRequest bookRequest) {
        return bookService.createBook(bookRequest);
    }

    @PutMapping("/books/{id}")
    public BookResponse updateBook(@PathVariable int id, @RequestBody BookUpdateRequest bookDetails) {
        return bookService.updateBook(id, bookDetails);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }
}
