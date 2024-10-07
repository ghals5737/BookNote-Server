package org.example.booknote.search.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.booknote.search.infrastructure.api.NaverBookSearchApi;
import org.example.booknote.search.infrastructure.chache.RedisRepository;
import org.example.booknote.search.infrastructure.config.NaverApiConfig;
import org.example.booknote.search.controller.port.SearchService;
import org.example.booknote.search.domain.BookSearch;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SearchServiceImpl implements SearchService {
    private final NaverBookSearchApi naverBookSearchApi;
    private final ObjectMapper objectMapper;
    private final RedisRepository redisRepository;

    public SearchServiceImpl(NaverBookSearchApi naverBookSearchApi,RedisRepository redisRepository) {
        this.naverBookSearchApi= naverBookSearchApi;
        this.objectMapper = new ObjectMapper();
        this.redisRepository=redisRepository;
    }

    @Override
    public List<BookSearch> searchBooks(String query){

        String redisKey = "search:" + query;
        String cachedResult = redisRepository.getCache(redisKey);
        if (cachedResult != null) {
            try {
                JsonNode items = objectMapper.readTree(cachedResult);
                return objectMapper.readValue(items.toString(), new TypeReference<>() {
                });
            } catch (Exception e) {
                throw new RuntimeException("Error parsing cached response", e);
            }
        }

        String result = naverBookSearchApi.search(query);

        try {
            JsonNode root = objectMapper.readTree(result);
            JsonNode items = root.path("items");

            // 결과를 Redis에 캐시하고 만료 시간 설정 (예: 1시간)
            redisRepository.setCache(redisKey, items.toString(), 1, TimeUnit.HOURS);

            return objectMapper.readValue(items.toString(), new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Error parsing response", e);
        }
    }
}
