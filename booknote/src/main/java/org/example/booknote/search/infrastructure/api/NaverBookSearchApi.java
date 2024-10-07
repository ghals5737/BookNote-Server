package org.example.booknote.search.infrastructure.api;

import org.example.booknote.search.infrastructure.config.NaverApiConfig;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class NaverBookSearchApi {
    private final RestTemplate restTemplate;
    private final NaverApiConfig naverApiConfig;

    public NaverBookSearchApi(RestTemplateBuilder builder, NaverApiConfig naverApiConfig) {
        this.restTemplate = builder.build();
        this.naverApiConfig = naverApiConfig;
    }

    public String search(String query) {
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

        return response.getBody();
    }
}
