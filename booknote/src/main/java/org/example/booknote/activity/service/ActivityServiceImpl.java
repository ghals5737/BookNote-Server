package org.example.booknote.activity.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.booknote.activity.controller.port.ActivityService;
import org.example.booknote.activity.controller.response.ActivityPageResponse;
import org.example.booknote.activity.domain.Activity;
import org.example.booknote.activity.domain.ActivitySearch;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    private final String topic="activity-log-topic";

    public ActivityServiceImpl(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
        this.objectMapper=new ObjectMapper();
        this.restTemplate=new RestTemplate();
    }


    @Override
    public void create(Activity activity) throws JsonProcessingException {
        String activityJson = objectMapper.writeValueAsString(activity);
        kafkaTemplate.send(topic, activityJson);
        System.out.println("Kafka로 로그 이벤트 발행됨: " + activity);
    }

    @Override
    public ActivityPageResponse getActivitiesByActorId(String actorId, int page, int size){
        String logServerUrl = "http://localhost:8090/api/activities?actorId=" + actorId + "&page=" + page + "&size=" + size;
        ResponseEntity<ActivityPageResponse> response = restTemplate.getForEntity(logServerUrl,ActivityPageResponse.class);
        return response.getBody();
    }

    @Override
    public ActivityPageResponse searchActivities(ActivitySearch activitySearch) {
        String logServerUrl = "http://localhost:8090/api/activities/search";
        ResponseEntity<ActivityPageResponse> response = restTemplate.postForEntity(logServerUrl, activitySearch, ActivityPageResponse.class);
        return response.getBody();
    }
}
