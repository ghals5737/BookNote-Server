package org.example.booknote.activity.infrastructure;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.booknote.activity.domain.Activity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "activity")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ActivityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private Long user_id;

    private Long book_id;

    private Long memo_id;

    private String description;

    @CreationTimestamp
    private LocalDateTime timestamp;

    public static ActivityEntity from(Activity activity) {
        ActivityEntity activityEntity=new ActivityEntity();
        activityEntity.id=activity.id();
        activityEntity.code=activity.code();
        activityEntity.user_id=activity.userId();
        activityEntity.book_id=activity.bookId();
        activityEntity.memo_id=activity.memoId();
        activityEntity.description= activity.description();
        activityEntity.timestamp=activity.timestamp();
        return activityEntity;
    }

    public Activity toModel() {
        return new Activity(
                id,
                code,
                user_id,
                book_id,
                memo_id,
                timestamp,
                description
        );
    }
}
