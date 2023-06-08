package org.clientserver.jwtappdem.security.jwt;

import org.clientserver.jwtappdem.model.User;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getUpdated()
                );
    }
}