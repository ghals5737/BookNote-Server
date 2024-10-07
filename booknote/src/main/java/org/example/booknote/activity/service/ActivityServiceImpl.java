package org.example.booknote.activity.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.booknote.activity.controller.port.ActivityService;
import org.example.booknote.activity.domain.Activity;
import org.example.booknote.activity.domain.ActivityCreate;
import org.example.booknote.activity.service.port.ActivityRepository;
import org.example.booknote.common.service.port.ClockHolder;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final ClockHolder clockHolder;


    @Override
    public Activity create(ActivityCreate activityCreate) {
        Activity activity=Activity.from(activityCreate,clockHolder);
        return activityRepository.save(activity);
    }
}
