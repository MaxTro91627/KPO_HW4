package org.clientserver.jwtappdem.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.clientserver.jwtappdem.model.User;
import org.clientserver.jwtappdem.repository.UserRepository;
import org.clientserver.jwtappdem.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User registeredUser = userRepository.save(user);

        log.info("register: {} registered!", registeredUser);
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("getAll: {} users", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("findByUsername: {} user found, username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("findById: no users found by {} id", id);
            return null;
        }
        log.info("findById: {} user found", result);
        return result;
    }

    @Override
    public User findByEmail(String email) {
        User result = userRepository.findByEmail(email);
        log.info("findByEmail: {} user found", result);
        return result;
    }

    @Override
    public boolean checkEmail(String email) {
        log.info("checkEmail: {}", email);
        return !email.contains("@");
    }
}
