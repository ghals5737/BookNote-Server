package org.example.booknote.activity.controller.port;

import org.example.booknote.activity.domain.Activity;
import org.example.booknote.activity.domain.ActivityCreate;

public interface ActivityService {
    Activity create(ActivityCreate activityCreate);
}
