package org.example.booknote.book.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BookCreate {
    private final String title;
    private final String author;

    @Builder
    public BookCreate(@JsonProperty("title") String title,@JsonProperty("author") String author) {
        this.title = title;
        this.author = author;
    }
}
