package org.clientserver.jwtappdem.dto;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String username;
    private String email;
    private String password;
    private String role;
}
