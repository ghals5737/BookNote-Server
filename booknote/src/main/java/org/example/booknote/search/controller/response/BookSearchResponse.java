package org.example.booknote.search.controller.response;

import org.example.booknote.search.domain.BookSearch;

public record BookSearchResponse(
        String title,
        String image,
        String author,
        String description,
        int index
) {
    public static BookSearchResponse from(BookSearch bookSearch,int index){
        return new BookSearchResponse(
                bookSearch.title(),
                bookSearch.image(),
                bookSearch.author(),
                bookSearch.description(),
                index
        );
    }
}
