package org.example.booknote.user.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.example.booknote.mock.FakeUserRepository;
import org.example.booknote.mock.TestClockHolder;
import org.example.booknote.mock.TestPasswordEncoder;
import org.example.booknote.user.domain.User;
import org.example.booknote.user.domain.UserCreate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

public class UserServiceTest {
    private UserServiceImpl userService;
    private TestPasswordEncoder passwordEncoder;

    @BeforeEach
    void init(){
        FakeUserRepository fakeUserRepository = new FakeUserRepository();
        passwordEncoder = new TestPasswordEncoder(new BCryptPasswordEncoder()); // Initialize passwordEncoder
        userService = UserServiceImpl.builder()
                .userRepository(fakeUserRepository)
                .clockHolder(new TestClockHolder())
                .passwordEncoder(passwordEncoder)
                .build();
    }

    @Test
    public void UserCreateDto를_사용하여_비밀번호를_암호화한다(){
        //given
        UserCreate userCreate = new UserCreate("test@test.com", "test", "test");

        //when
        User result=userService.create(userCreate);
        //then
        assertThat(result).isNotNull();
        assertThat(passwordEncoder.matches("test", result.password())).isTrue();
    }

    @Test
    public void UserCreateDto를_사용하여_사용자를_저장한다(){
        //given
        UserCreate userCreate = new UserCreate("test@test.com", "test", "test");

        //when
        User result = userService.create(userCreate);

        //then
        assertThat(result).isNotNull();
        assertThat(passwordEncoder.matches("test", result.password())).isTrue();
    }

    @Test
    public void UserLoginDto를_사용하여_로그인인증을한다(){
        //given
        UserCreate userCreate = new UserCreate("test@test.com", "test", "test");
        userService.create(userCreate);

        //when
        Boolean loginStatus = userService.authenticate("test@test.com", "test");

        //then
        assertThat(loginStatus).isTrue();
    }
}
