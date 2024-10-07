package org.example.booknote.activity.infrastructure;

import lombok.RequiredArgsConstructor;
import org.example.booknote.activity.domain.Activity;
import org.example.booknote.activity.service.port.ActivityRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ActivityRepositoryImpl implements ActivityRepository {
    private final ActivityJpaRepository activityJpaRepository;

    @Override
    public Activity save(Activity activity) {
        return activityJpaRepository.save(ActivityEntity.from(activity)).toModel();
    }
}
