package com.shapca.splitaccountapi.service;

import com.shapca.splitaccountapi.domain.User;
import com.shapca.splitaccountapi.form.UserData;
import com.shapca.splitaccountapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final JWTService jwtService;

    public UserService(UserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public String register(UserData userData) {
        User user = new User();
        user.setLogin(userData.getLogin());
        user.setPasswordSha(userData.getPassword());
        return jwtService.createJWT(userRepository.save(user));
    }

    public String sign(UserData userData) {
        User user = new User();
        user.setLogin(userData.getLogin());
        user.setPasswordSha(userData.getPassword());
        return jwtService.createJWT(userRepository.findByLoginAndPasswordSha(userData.getLogin(), user.getPasswordSha()));
    }

    public User findById(long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User findByJWT(String jwt) {
        return findById(jwtService.decodeJWT(jwt));
    }

    public boolean isLoginVacant(String login) {
        return userRepository.countByLogin(login) == 0;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
