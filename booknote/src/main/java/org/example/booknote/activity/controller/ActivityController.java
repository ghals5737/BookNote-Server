package org.example.booknote.activity.controller;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.booknote.activity.controller.port.ActivityService;
import org.example.booknote.activity.controller.response.ActivityResponse;
import org.example.booknote.activity.domain.ActivityCreate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activity")
@Builder
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> create(@RequestBody ActivityCreate activityCreate){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ActivityResponse.from(activityService.create(activityCreate)));
    }
}
