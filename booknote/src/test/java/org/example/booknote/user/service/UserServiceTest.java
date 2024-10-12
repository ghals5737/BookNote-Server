package org.example.booknote.user.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.jsonwebtoken.Claims;
import org.example.booknote.mock.FakeSessionRedisRepository;
import org.example.booknote.mock.FakeUserRepository;
import org.example.booknote.mock.TestClockHolder;
import org.example.booknote.mock.TestPasswordEncoder;
import org.example.booknote.user.domain.User;
import org.example.booknote.user.domain.UserCreate;
import org.example.booknote.user.domain.UserLogin;
import org.example.booknote.user.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class UserServiceTest {
    private UserServiceImpl userService;

    @BeforeEach
    void init(){
        FakeUserRepository fakeUserRepository = new FakeUserRepository();
        FakeSessionRedisRepository fakeSessionRedisRepository = new FakeSessionRedisRepository();
        JwtUtil jwtUtil=new JwtUtil();

        userService = UserServiceImpl.builder()
                .jwtUtil(jwtUtil)
                .userRepository(fakeUserRepository)
                .sessionRedisRepository(fakeSessionRedisRepository)
                .clockHolder(new TestClockHolder())
                .build();
    }

    @Test
    public void UserCreateDto를_사용하여_사용자를_저장한다(){
        //given
        UserCreate userCreate = new UserCreate("test@test.com", "test", "test");

        //when
        User result = userService.create(userCreate);

        //then
        assertThat(result).isNotNull();
    }

    @Test
    public void UserDto를_사용하여_jwt토큰을_생성한다(){
        //given
        User user1=new User(
                1L,
                "test1@naver.com",
                "user1",
                "picture1",
                LocalDateTime.now()
                ,LocalDateTime.now()
        );
        int duration=1000;

        //when
        String result= userService.tokenCreate(user1,duration);

        //then
        assertThat(result).isNotNull();
    }

    @Test
    public void jwt_토큰에서_값을_얻는다(){
        //given
        User user1=new User(
                1L,
                "test1@naver.com",
                "user1",
                "picture1",
                LocalDateTime.now()
                ,LocalDateTime.now()
        );
        int duration=1000*60*10;
        String token= userService.tokenCreate(user1,duration);

        //when
        User result=userService.tokenConvertUser(token);

        //then
        assertThat(result.id()).isEqualTo(user1.id());
        assertThat(result.email()).isEqualTo(user1.email());
        assertThat(result.name()).isEqualTo(user1.name());
        assertThat(result.picture()).isEqualTo(user1.picture());
    }

    @Test
    public void jwt_토큰_만료확인() throws InterruptedException {
        //given
        User user = new User(1L, "test@test.com", "user", "picture", LocalDateTime.now(), LocalDateTime.now());

        // 1초짜리 짧은 유효기간 토큰 생성
        int duration=-1000*2;
        String token= userService.tokenCreate(user,duration);

        //when
        boolean isExpired = userService.isTokenExpired(token);

        //then
        assertThat(isExpired).isTrue();
    }

    @Test
    public void 사용자_존재할_경우_Redis에서_토큰_가져오기() {
        //given
        User user = new User(1L, "test@test.com", "user", "picture", LocalDateTime.now(), LocalDateTime.now());
        int duration=1000;
        String token= userService.tokenCreate(user,duration);

        //when
        String redisToken = userService.tokenFindById(String.valueOf(user.id()));

        //then
        assertThat(redisToken).isNotNull();
        assertThat(redisToken).isEqualTo(token);
    }

    @Test
    public void 토큰의_유효성을확인한다() {
        //given
        User user = new User(1L, "test@test.com", "user", "picture", LocalDateTime.now(), LocalDateTime.now());
        int duration=1000*60*60*6;
        String token= userService.tokenCreate(user,duration);

        //when
        boolean isAuthorized = userService.isUserAuthorized(token);

        //then
        assertThat(isAuthorized).isTrue();  // 인증 실패
    }

    @Test
    public void 이메일로_사용자를_검색한다() {
        //given
        User user = userService.create(new UserCreate("test@test.com", "test", "test"));

        //when
        User foundUser = userService.findByEmail("test@test.com");

        //then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.email()).isEqualTo(user.email());
    }

    @Test
    public void 아이디로_사용자를_검색한다() {
        //given
        User user = userService.create(new UserCreate("test@test.com", "test", "test"));

        //when
        User foundUser = userService.getUserById(user.id());

        //then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.id()).isEqualTo(user.id());
    }

    @Test
    public void UserCreateDto로_로그인한다() {
        //given
        User user = new User(1L, "test@test.com", "user", "picture", LocalDateTime.now(), LocalDateTime.now());

        //when
        String redisToken= userService.login(new UserCreate("test@test.com", "user", "picture"));
        User result=userService.tokenConvertUser(redisToken);

        //then
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(user.id());
        assertThat(result.name()).isEqualTo(user.name());
        assertThat(result.email()).isEqualTo(user.email());
        assertThat(result.picture()).isEqualTo(user.picture());
    }
}
