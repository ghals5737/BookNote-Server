package org.example.booknote.user.controller;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.booknote.user.controller.port.UserService;
import org.example.booknote.user.controller.response.UserResponse;
import org.example.booknote.user.domain.UserCreate;
import org.example.booknote.user.domain.UserLogin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Builder
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserCreate userCreate) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserResponse.from(userService.create(userCreate)));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserLogin userLogin) {
        return ResponseEntity
                .ok()
                .body(UserResponse.from(userService.login(userLogin)));
    }
}
