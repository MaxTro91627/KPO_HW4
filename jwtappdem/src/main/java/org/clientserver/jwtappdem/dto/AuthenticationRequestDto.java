package org.clientserver.jwtappdem.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String email;
    private String password;
}