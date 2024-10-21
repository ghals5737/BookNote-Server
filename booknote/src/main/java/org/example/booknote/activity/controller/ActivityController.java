package org.example.booknote.activity.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.booknote.activity.controller.port.ActivityService;
import org.example.booknote.activity.controller.response.ActivityPageResponse;
import org.example.booknote.activity.domain.Activity;
import org.example.booknote.activity.domain.ActivitySearch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activity")
@Builder
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Activity activity) throws JsonProcessingException {
        activityService.create(activity);

        return ResponseEntity.ok("messege pub success");
    }

    @GetMapping
    public ResponseEntity<ActivityPageResponse> getActivities(
            @RequestParam String actorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(activityService.getActivitiesByActorId(actorId,page,size));
    }

    @PostMapping("/search")
    public ResponseEntity<ActivityPageResponse> searchActivities(@RequestBody ActivitySearch activitySearch) {
        return ResponseEntity.ok(activityService.searchActivities(activitySearch));
    }
}
