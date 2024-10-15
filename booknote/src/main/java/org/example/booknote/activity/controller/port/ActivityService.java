package org.example.booknote.activity.controller.port;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.booknote.activity.controller.response.ActivityPageResponse;
import org.example.booknote.activity.domain.Activity;
import org.example.booknote.activity.domain.ActivitySearch;

import java.util.List;

public interface ActivityService {
    void create(Activity activity) throws JsonProcessingException;
    ActivityPageResponse getActivitiesByActorId(String actorId, int page, int size);
    ActivityPageResponse searchActivities(ActivitySearch activitySearch);
}
