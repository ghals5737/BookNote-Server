package org.example.booknote.user.controller.port;

import org.example.booknote.user.domain.User;
import org.example.booknote.user.domain.UserCreate;
import org.example.booknote.user.domain.UserLogin;

public interface UserService {
    User getUserById(long id);
    User create(UserCreate userCreate);
    User login(UserLogin userLogin);
    User findByEmail(String email);
    Boolean authenticate(String email, String password);
}
