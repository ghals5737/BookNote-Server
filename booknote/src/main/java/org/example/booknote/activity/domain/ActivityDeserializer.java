package org.example.booknote.activity.domain;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.booknote.activity.domain.target.Target;
import org.example.booknote.activity.domain.target.book.BookTarget;
import org.example.booknote.activity.domain.target.book.BookUpdateTarget;
import org.example.booknote.activity.domain.target.memo.MemoTarget;
import org.example.booknote.activity.domain.target.memo.MemoUpdateTarget;
import org.example.booknote.activity.domain.target.user.UserTarget;
import org.example.booknote.activity.domain.target.user.UserUpdateTarget;
import org.example.booknote.common.service.port.ClockHolder;

import java.io.IOException;

public class ActivityDeserializer extends JsonDeserializer<Activity> {
    private final ObjectMapper objectMapper;

    public ActivityDeserializer() {
        this.objectMapper=new ObjectMapper();
    }


    @Override
    public Activity deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        // 전체 JSON 트리
        JsonNode rootNode = p.getCodec().readTree(p);

        // 각 필드 추출
        String id = rootNode.get("id").asText();
        String action = rootNode.get("action").asText();
        JsonNode actorNode = rootNode.get("actor");
        JsonNode targetNode = rootNode.get("target");
        long timestamp = rootNode.get("timestamp").asLong();

        // Actor는 간단히 처리 가능하다고 가정
        Actor actor = objectMapper.readValue(actorNode.toString(), Actor.class);

        // Target 필드 처리: action에 따라 구체적인 타입으로 변환
        Target target = determineTargetType(action, targetNode);

        return new Activity(id, action, actor, target, timestamp);
    }

    // action에 따라 구체적인 Target 클래스를 결정하는 로직
    private Target determineTargetType(String action, JsonNode targetNode) throws IOException {
        return switch (action) {
            case "book.select", "book.delete", "book.create" -> objectMapper.readValue(targetNode.toString(), new TypeReference<BookTarget>() {});
            case "book.update" -> objectMapper.readValue(targetNode.toString(), new TypeReference<BookUpdateTarget>() {});
            case "memo.select", "memo.delete", "memo.create" -> objectMapper.readValue(targetNode.toString(), new TypeReference<MemoTarget>() {});
            case "memo.update" -> objectMapper.readValue(targetNode.toString(), new TypeReference<MemoUpdateTarget>() {});
            case "user.create", "user.select" -> objectMapper.readValue(targetNode.toString(), new TypeReference<UserTarget>() {});
            case "user.update" -> objectMapper.readValue(targetNode.toString(), new TypeReference<UserUpdateTarget>() {});
            default -> throw new RuntimeException("Unknown action type: " + action);
        };
    }
}
