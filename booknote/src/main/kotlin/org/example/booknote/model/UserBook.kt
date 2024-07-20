package org.example.booknote.model

import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "user_books")
data class UserBook(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        var user: User,

        @ManyToOne
        @JoinColumn(name = "book_id", nullable = false)
        var book: Book,

        val rating: Int? = null,

        @Column(nullable = false)
        val bookmark: Boolean = false,

        @Column(name = "created_at", nullable = false, updatable = false)
        val createdAt: LocalDateTime = LocalDateTime.now(),

        @Column(name = "updated_at", nullable = false)
        val updatedAt: LocalDateTime = LocalDateTime.now()
)

