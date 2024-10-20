package org.example.booknote.user.controller.port;

import org.example.booknote.user.domain.Token;
import org.example.booknote.user.domain.User;
import org.example.booknote.user.domain.UserCreate;
import org.example.booknote.user.domain.UserLogin;

public interface UserService {
    User getUserById(long id);
    User create(UserCreate userCreate);
    Token login(UserCreate userCreate);
    String tokenFindById(String id);
    String tokenCreate(User user,int duration);
    boolean isTokenExpired(String token);
    boolean isUserAuthorized(String token);
    User tokenConvertUser(String token);
    String refreshAccessToken(Token token);
}


/*
* 유저 가입 -> 이메일로 검색
* 있는경우 redis에서 id로 검색해 토큰 줌
* 없는경우 db에 저장 토큰 만든후 redis에 저장
*
* 정보 수정 -> 아이디로 검색
* db업데이트 토큰재발급후 redis 갱신
*
* jwt
* 토큰 생성
* 토큰 파싱
* 만료 기능 검사
*
* 인증 기능
*   만료 확인
*   레디스에 있는지 확인
*   있으면 pass 없으면 403 error
*
* 이메일로 검색
*
* 아이디로 검색
*
* 레디스
*   아이디로 검색
*   아이디,토큰 키밸류로 저장
* */