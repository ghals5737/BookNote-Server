package org.example.booknote.user.service;

import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.booknote.common.domain.exception.ResourceNotFoundException;
import org.example.booknote.common.service.port.ClockHolder;
import org.example.booknote.user.controller.port.UserService;
import org.example.booknote.user.domain.User;
import org.example.booknote.user.domain.UserCreate;
import org.example.booknote.user.domain.UserLogin;
import org.example.booknote.user.infrastructure.SessionRedisRepositoryImpl;
import org.example.booknote.user.service.port.SessionRedisRepository;
import org.example.booknote.user.service.port.UserRepository;
import org.example.booknote.user.util.JwtUtil;
import org.springframework.stereotype.Service;

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
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Users",id));
    }

    @Override
    public User create(UserCreate userCreate) {
        User user=User.from(userCreate,clockHolder);
        return userRepository.save(user);
    }

    @Override
    public String login(UserCreate userCreate) {
        Optional<User> existingUser = userRepository.findByEmail(userCreate.email());

        if (existingUser.isEmpty()) {
            User newUser = User.from(userCreate, clockHolder);
            User savedUser = userRepository.save(newUser);

            return tokenCreate(savedUser, 1000*60*60*6);
        }

        User user = existingUser.get();

        String redisToken = tokenFindById(String.valueOf(user.id()));

        if (redisToken == null) {
            redisToken = tokenCreate(user, 1000*60*60*6);
        }

        return redisToken;
    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Users",email));
    }

    @Override
    public String tokenFindById(String id) {
        return sessionRedisRepository.getToken(id);
    }

    @Override
    public String tokenCreate(User user,int duration) {
        String token=jwtUtil.generateToken(user,duration);
        sessionRedisRepository.setToken(
                String.valueOf(user.id()),
                token,
                duration,
                TimeUnit.MILLISECONDS
        );
        return token;
    }

    @Override
    public boolean isTokenExpired(String token) {
        return jwtUtil.isTokenExpired(token);
    }

    @Override
    public boolean isUserAuthorized(String token) {
        try {
            // 1. 토큰 만료 여부 확인
            if (this.isTokenExpired(token)) {
                return false;  // 토큰이 만료되면 인증 실패
            }

            // 2. 토큰에서 사용자 정보 변환
            User user = this.tokenConvertUser(token);
            if (user == null) {
                return false;  // 토큰에서 사용자 정보를 추출하지 못하면 인증 실패
            }

            // 3. Redis에서 토큰 조회
            String redisToken = this.tokenFindById(String.valueOf(user.id()));
            if (redisToken == null) {
                // 4. Redis에 없으면 DB에서 사용자 확인
                Optional<User> userInDb = userRepository.findById(user.id());
                return userInDb.isPresent();  // DB에도 사용자가 없으면 인증 실패
            }

            // 인증 성공
            return true;

        } catch (Exception e) {
            // 예외가 발생한 경우 인증 실패 처리
            return false;
        }
    }


    @Override
    public User tokenConvertUser(String token) {
        Claims claims= jwtUtil.extractAllClaims(token);

        Object idObject = claims.get("id");
        Long id = null;

        if (idObject instanceof Integer) {
            id = ((Integer) idObject).longValue();
        } else if (idObject instanceof Long) {
            id = (Long) idObject;
        }

        return new User(
                id,
                (String) claims.get("email"),
                (String) claims.get("name"),
                (String) claims.get("picture"),
                clockHolder.now(),
                clockHolder.now()
        );
    }
}
