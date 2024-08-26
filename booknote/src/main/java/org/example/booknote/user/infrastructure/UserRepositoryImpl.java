package org.example.booknote.user.infrastructure;

import lombok.RequiredArgsConstructor;
import org.example.booknote.user.domain.User;
import org.example.booknote.user.service.port.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public User getById(long id) {
        return null;
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String username) {
        return Optional.empty();
    }
}
