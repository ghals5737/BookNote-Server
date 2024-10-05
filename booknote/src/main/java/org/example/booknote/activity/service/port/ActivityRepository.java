package org.example.booknote.activity.service.port;

import org.example.booknote.activity.domain.Activity;

public interface ActivityRepository {
    Activity save(Activity activity);
}
