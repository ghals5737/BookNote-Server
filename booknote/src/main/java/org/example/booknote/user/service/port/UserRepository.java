package org.example.booknote.user.service.port;

import org.example.booknote.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    User getById(long id);
    Optional<User> findById(long id);
    User save(User user);
}
