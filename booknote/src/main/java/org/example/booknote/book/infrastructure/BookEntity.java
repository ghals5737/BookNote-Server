package org.example.booknote.book.infrastructure;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.booknote.book.domain.Book;
import org.example.booknote.user.infrastructure.UserEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="books")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String author;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    public static BookEntity from(Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.id=book.id();
        bookEntity.title=book.title();
        bookEntity.author=book.author();
        bookEntity.user=UserEntity.from(book.user());
        bookEntity.createAt=book.createAt();
        bookEntity.updateAt=book.updateAt();
        return bookEntity;
    }

    public Book toModel(){
        return new Book(id,title,author,user.toModel(),createAt,updateAt);
    }
}
