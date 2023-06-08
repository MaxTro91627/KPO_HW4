package org.clientserver.jwtappdem.service;

import org.clientserver.jwtappdem.model.User;

import java.util.List;

public interface UserService {
    void register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    User findByEmail(String email);

    boolean checkEmail(String email);

}
