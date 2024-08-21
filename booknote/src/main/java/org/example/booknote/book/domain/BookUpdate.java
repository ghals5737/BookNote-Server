package org.example.booknote.book.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BookUpdate {
    private final String title;

    @Builder
    public BookUpdate(@JsonProperty("title") String title) {
        this.title = title;
    }
}
