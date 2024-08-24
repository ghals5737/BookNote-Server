package org.example.booknote.memo.infrastructure;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.booknote.book.infrastructure.BookEntity;
import org.example.booknote.memo.domain.Memo;
import org.example.booknote.user.infrastructure.UserEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="memos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemoEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @Column(nullable = false)
    private String memo;

    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    public static MemoEntitiy from(Memo memo){
        MemoEntitiy memoEntitiy= new MemoEntitiy();
        memoEntitiy.id=memo.id();
        memoEntitiy.book=BookEntity.from(memo.book());
        memoEntitiy.memo=memo.memo();
        memoEntitiy.createAt=memo.createAt();
        memoEntitiy.updateAt=memo.updateAt();
        return memoEntitiy;
    }

    public Memo toModel() {
        return new Memo(
                id,
                book.toModel(),
                memo,
                createAt,
                updateAt
        );
    }
}
