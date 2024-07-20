package org.example.booknote.model

import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "notes")
data class Note(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @ManyToOne
        @JoinColumn(name = "book_id", nullable = false)
        var book: Book,

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        var user: User,

        @Column(nullable = false)
        val note: String,

        @Column(name = "timestamp", nullable = false)
        val timestamp: LocalDateTime = LocalDateTime.now(),

        @Column(name = "created_at", nullable = false, updatable = false)
        val createdAt: LocalDateTime = LocalDateTime.now(),

        @Column(name = "updated_at", nullable = false)
        val updatedAt: LocalDateTime = LocalDateTime.now()
)
