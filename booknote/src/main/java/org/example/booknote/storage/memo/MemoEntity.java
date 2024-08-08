package org.example.booknote.storage.memo;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.booknote.storage.BaseEntity;
import org.example.booknote.storage.book.BookEntity;
import org.example.booknote.storage.user.UserEntity;

@Entity
@Table(name="memos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemoEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private String memo;

}
