package org.example.booknote.storage.book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.booknote.domain.book.Book;
import org.example.booknote.storage.BaseEntity;

@Entity
@Table(name="books")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookEntity extends BaseEntity {
    @Column(nullable = false)
    private String title;
    private String author;

    public BookEntity(String title,String author){
        this.title=title;
        this.author=author;
    }

    public Book toBook(){
        return new Book(getId(),title,author,getCreateAt());
    }
}
