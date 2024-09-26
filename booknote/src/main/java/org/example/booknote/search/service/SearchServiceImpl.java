package org.example.booknote.search.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.booknote.config.NaverApiConfig;
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

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    private final RestTemplate restTemplate;
    private final NaverApiConfig naverApiConfig;
    private final ObjectMapper objectMapper;

    public SearchServiceImpl(RestTemplateBuilder builder, NaverApiConfig naverApiConfig,ObjectMapper objectMapper) {
        this.restTemplate = builder.build();
        this.naverApiConfig = naverApiConfig;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public List<BookSearch> searchBooks(String query){
        // 쿼리 스트링 생성
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/book.json")
                .queryParam("query", query)
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "sim")
                .encode()
                .build()
                .toUri();

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverApiConfig.getClientId());
        headers.set("X-Naver-Client-Secret", naverApiConfig.getClientSecret());

        // 요청 생성
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // API 호출
        ResponseEntity<String> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                entity,
                String.class
        );

        String result=response.getBody();

        try {
            JsonNode root = objectMapper.readTree(result);
            JsonNode items = root.path("items");

            return objectMapper.readValue(items.toString(), new TypeReference<List<BookSearch>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error parsing response", e);
        }
    }
}
