package org.example.booknote.search.controller;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.booknote.search.controller.port.SearchService;
import org.example.booknote.search.controller.response.BookSearchResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/api/search")
@Builder
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/books")
    public ResponseEntity<List<BookSearchResponse>> searchBooks(@RequestParam String query) {
        return ResponseEntity
                .ok()
                .body(searchService.searchBooks(query).stream().map(BookSearchResponse::from).toList());
    }
}
