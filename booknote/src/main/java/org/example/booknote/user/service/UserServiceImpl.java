package org.example.booknote.user.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.booknote.common.domain.exception.ResourceNotFoundException;
import org.example.booknote.common.service.port.ClockHolder;
import org.example.booknote.common.service.port.PasswordEncoder;
import org.example.booknote.user.controller.port.UserService;
import org.example.booknote.user.domain.User;
import org.example.booknote.user.domain.UserCreate;
import org.example.booknote.user.domain.UserLogin;
import org.example.booknote.user.service.port.UserRepository;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ClockHolder clockHolder;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Users",id));
    }

    @Override
    public User create(UserCreate userCreate) {
        User user=User.from(userCreate,clockHolder,passwordEncoder);
        return userRepository.save(user);
    }

    @Override
    public User login(UserLogin userLogin) {
        User user=findByEmail(userLogin.email());
        if(authenticate(user,userLogin)){
            return user;
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Users",email));
    }

    @Override
    public Boolean authenticate(User user,UserLogin userLogin) {
        return passwordEncoder.matches(userLogin.password(), user.password());
    }
}
