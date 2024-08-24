package org.example.booknote.user.controller.port;

import org.example.booknote.user.domain.User;
import org.example.booknote.user.domain.UserCreate;

public interface UserService {
    User getUserById(long id);
    User create(UserCreate userCreate);

}
