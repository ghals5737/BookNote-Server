package org.example.booknote.mock;

import org.example.booknote.activity.domain.Activity;
import org.example.booknote.activity.service.port.ActivityRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class FakeActivityRepository implements ActivityRepository {
    private final AtomicLong autoGeneratedId = new AtomicLong(0);
    private final List<Activity> activities = Collections.synchronizedList(new ArrayList<>());
}
