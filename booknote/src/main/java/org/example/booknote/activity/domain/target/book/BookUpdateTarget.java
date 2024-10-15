package org.example.booknote.activity.domain.target.book;

import org.example.booknote.activity.domain.target.Target;

public record BookUpdateTarget(
        BookTarget before,
        BookTarget after
) implements Target {}