package org.example.booknote.search.domain;

public record BookSearch(
        String title,
        String link,
        String image,
        String author,
        String discount,
        String publisher,
        String pubdate,
        String isbn,
        String description

) {
}
