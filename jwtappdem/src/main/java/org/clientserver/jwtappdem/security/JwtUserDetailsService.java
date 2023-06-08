package org.clientserver.jwtappdem.security;

import lombok.extern.slf4j.Slf4j;
import org.clientserver.jwtappdem.model.User;
import org.clientserver.jwtappdem.security.jwt.JwtUser;
import org.clientserver.jwtappdem.security.jwt.JwtUserFactory;
import org.clientserver.jwtappdem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("User " + username + "not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("loadUserByUsername: username {} user loaded", username);
        return jwtUser;
    }
}
