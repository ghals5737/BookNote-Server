package org.example.booknote

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BooknoteApplication

fun main(args: Array<String>) {
	runApplication<BooknoteApplication>(*args)
}
