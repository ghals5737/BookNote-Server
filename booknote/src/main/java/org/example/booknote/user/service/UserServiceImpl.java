package org.example.booknote.user.service;

import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.booknote.common.domain.exception.ResourceNotFoundException;
import org.example.booknote.common.service.port.ClockHolder;
import org.example.booknote.user.controller.port.UserService;
import org.example.booknote.user.domain.Token;
import org.example.booknote.user.domain.User;
import org.example.booknote.user.domain.UserCreate;
import org.example.booknote.user.service.port.SessionRedisRepository;
import org.example.booknote.user.service.port.UserRepository;
import org.example.booknote.user.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Builder
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ClockHolder clockHolder;
    private final JwtUtil jwtUtil;
    private final SessionRedisRepository sessionRedisRepository;

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Users", id));
    }

    @Override
    public User create(UserCreate userCreate) {
        User user = User.from(userCreate, clockHolder);
        return userRepository.save(user);
    }

    @Override
    public Token login(UserCreate userCreate) {
        Optional<User> existingUser = userRepository.findByEmail(userCreate.email());

        if (existingUser.isEmpty()) {
            User newUser = User.from(userCreate, clockHolder);
            User savedUser = userRepository.save(newUser);

            return generateTokens(savedUser);
        }

        User user = existingUser.get();
        String redisToken = tokenFindById(String.valueOf(user.id()));

        if (redisToken == null) {
            return generateTokens(user);
        }

        return generateTokens(user);
    }

    @Override
    public String tokenFindById(String id) {
        return sessionRedisRepository.getToken(id);
    }

    @Override
    public String tokenCreate(User user, int duration) {
        String token = jwtUtil.generateToken(user, duration);
        sessionRedisRepository.setToken(
                String.valueOf(user.id()),
                token,
                duration,
                TimeUnit.MILLISECONDS
        );
        return token;
    }

    @Override
    public String refreshAccessToken(Token token) {
        User convertUser = this.tokenConvertUser(token.access_token());
        Claims claims = jwtUtil.extractAllClaims(token.refresh_token());
        String userId = claims.getSubject();

        if(this.isTokenExpired(token.refresh_token())){
            return null;
        }

        String storedRefreshToken = sessionRedisRepository.getRefreshToken(userId);
        if (storedRefreshToken == null || !storedRefreshToken.equals(token.refresh_token())) {
            return null;
        }

        User user = getUserById(Long.parseLong(userId));

        if(convertUser.id()!=user.id()||
            !convertUser.email().equals(user.email())||
            !convertUser.name().equals(user.email())){
            return null;
        }

        return jwtUtil.generateToken(user, 1000 * 60 * 60);
    }

    public Token generateTokens(User user) {
        String accessToken = jwtUtil.generateToken(user, 1000 * 60 * 60);
        String refreshToken = jwtUtil.generateRefreshToken(user, 1000 * 60 * 60 * 24 * 7);

        sessionRedisRepository.setToken(String.valueOf(user.id()), accessToken, 1000 * 60 * 60, TimeUnit.MILLISECONDS);
        sessionRedisRepository.setRefreshToken(String.valueOf(user.id()), refreshToken, 1000 * 60 * 60 * 24 * 7, TimeUnit.MILLISECONDS);

        return new Token(accessToken,refreshToken);
    }

    @Override
    public boolean isTokenExpired(String token) {
        return jwtUtil.isTokenExpired(token);
    }

    @Override
    public boolean isUserAuthorized(String token) {
        try {
            if (this.isTokenExpired(token)) {
                return false;
            }

            User user = this.tokenConvertUser(token);
            if (user == null) {
                return false;
            }

            String redisToken = this.tokenFindById(String.valueOf(user.id()));
            if(redisToken==null){
                return false;
            }
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User tokenConvertUser(String token) {
        Claims claims = jwtUtil.extractAllClaims(token);
        return new User(
                ((Integer) claims.get("id")).longValue(),
                (String) claims.get("email"),
                (String) claims.get("name"),
                (String) claims.get("picture"),
                null,
                null
        );
    }
}

