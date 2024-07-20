package org.example.booknote.model

import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "books")
data class Book(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @Column(nullable = false)
        val title: String,

        val author: String? = null,

        @Column(name = "created_at", nullable = false, updatable = false)
        val createdAt: LocalDateTime = LocalDateTime.now(),

        @Column(name = "updated_at", nullable = false)
        val updatedAt: LocalDateTime = LocalDateTime.now()
)
