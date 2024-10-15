package org.example.booknote.activity.domain.target.memo;

import org.example.booknote.activity.domain.target.Target;

public record MemoUpdateTarget(
        MemoTarget before,
        MemoTarget after
) implements Target {}
