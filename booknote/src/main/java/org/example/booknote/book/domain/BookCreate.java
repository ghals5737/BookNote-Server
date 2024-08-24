package org.example.booknote.book.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

public record BookCreate(long userId,String title, String author) {

}
