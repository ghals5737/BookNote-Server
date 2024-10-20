package org.example.booknote.user.controller;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.booknote.user.controller.port.UserService;
import org.example.booknote.user.controller.response.UserResponse;
import org.example.booknote.user.domain.Token;
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
    public ResponseEntity<Token> login(@RequestBody UserCreate userCreate) {
        return ResponseEntity
                .ok()
                .body(userService.login(userCreate));
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken(@RequestBody Token token) {
        String access_token= userService.refreshAccessToken(token);
        if(access_token==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }
        return ResponseEntity
                .ok()
                .body(access_token);
    }
}
