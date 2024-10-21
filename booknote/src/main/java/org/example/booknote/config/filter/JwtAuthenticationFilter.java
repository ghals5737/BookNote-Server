package org.example.booknote.config.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.booknote.user.controller.port.UserService;
import org.example.booknote.user.domain.Token;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserService userService;

    public JwtAuthenticationFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        String url=request.getRequestURI();

        if(!url.startsWith("/api")){
            filterChain.doFilter(request, response);
            return;
        }

        if(url.equals("/api/users/login")||url.equals("/api/users/refresh")){
            filterChain.doFilter(request, response); // 필터 체인을 계속 진행
            return;
        }

        if(cookies==null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            filterChain.doFilter(request, response);
            return;
        }

        Optional<String> access_token = Arrays.stream(cookies)
                .filter(cookie -> "access_token".equals(cookie.getName())) // 쿠키 이름 필터링
                .map(Cookie::getValue) // 값 추출
                .findFirst();

        Optional<String> refresh_token = Arrays.stream(cookies)
                .filter(cookie -> "refresh_token".equals(cookie.getName())) // 쿠키 이름 필터링
                .map(Cookie::getValue) // 값 추출
                .findFirst();

        if(!(access_token.isPresent()&&refresh_token.isPresent())){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        if(!userService.isUserAuthorized(access_token.get())){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        filterChain.doFilter(request, response);
    }
}

