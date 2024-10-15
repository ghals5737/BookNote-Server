package org.example.booknote.activity.domain;

import java.util.List;

public record ActivitySearch(
        long actorId,
        long from,
        long to,
        List<String> searchActions,
        int page,
        int size
) {
}
