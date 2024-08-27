package org.example.booknote.user.infrastructure;

import lombok.RequiredArgsConstructor;
import org.example.booknote.common.domain.exception.ResourceNotFoundException;
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
        return findById(id).orElseThrow(()->new ResourceNotFoundException("Users",id));
    }

    @Override
    public Optional<User> findById(long id) {
        return userJpaRepository.findById(id).map(UserEntity::toModel);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(UserEntity.from(user)).toModel();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(UserEntity::toModel);
    }
}
