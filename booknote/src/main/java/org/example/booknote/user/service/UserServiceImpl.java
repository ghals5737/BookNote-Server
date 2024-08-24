package org.example.booknote.user.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.booknote.common.domain.exception.ResourceNotFoundException;
import org.example.booknote.common.service.port.ClockHolder;
import org.example.booknote.user.controller.port.UserService;
import org.example.booknote.user.domain.User;
import org.example.booknote.user.domain.UserCreate;
import org.example.booknote.user.service.port.UserRepository;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ClockHolder clockHolder;

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Users",id));
    }

    @Override
    public User create(UserCreate userCreate) {
        User user=User.from(userCreate,clockHolder);
        return userRepository.save(user);
    }
}
