package org.example.booknote.search.controller.port;

import org.example.booknote.search.domain.BookSearch;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface SearchService {
    List<BookSearch> searchBooks(String query);
}
