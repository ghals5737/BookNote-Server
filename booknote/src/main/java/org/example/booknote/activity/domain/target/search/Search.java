package org.example.booknote.activity.domain.target.search;

import org.example.booknote.activity.domain.target.Target;

// Search record
public record Search(
        String query
) implements Target {}
