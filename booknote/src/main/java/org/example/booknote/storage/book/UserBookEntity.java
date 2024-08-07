package org.example.booknote.storage.book;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.booknote.storage.BaseEntity;
import org.example.booknote.storage.user.UserEntity;

@Entity
@Table(name="user_books")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBookEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity bookEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @Column(nullable = false)
    private boolean bookmark;

    @Column
    private Integer rating;
}
