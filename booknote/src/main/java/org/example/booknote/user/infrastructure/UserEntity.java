package org.example.booknote.user.infrastructure;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.booknote.user.domain.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String picture;

    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.id=user.id();
        userEntity.email=user.email();
        userEntity.name=user.name();
        userEntity.picture=user.picture();
        userEntity.createAt=user.createAt();
        userEntity.updateAt=user.updateAt();
        return userEntity;
    }

    public User toModel() {
        return new User(id, email, name, picture, createAt, updateAt);
    }
}
