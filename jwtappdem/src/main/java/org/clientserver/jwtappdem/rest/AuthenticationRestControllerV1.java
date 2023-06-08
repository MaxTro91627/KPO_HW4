package org.clientserver.jwtappdem.rest;

import lombok.RequiredArgsConstructor;
import org.clientserver.jwtappdem.dto.AuthenticationRequestDto;
import org.clientserver.jwtappdem.dto.SignUpRequestDto;
import org.clientserver.jwtappdem.model.User;
import org.clientserver.jwtappdem.security.jwt.JwtTokenProvider;
import org.clientserver.jwtappdem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    requestDto.getEmail(),
                    requestDto.getPassword()
            );
            authenticationManager.authenticate(authToken);
            User user = userService.findByEmail(requestDto.getEmail());
            if (user == null) {
                throw new UsernameNotFoundException("Email " + requestDto.getEmail() + " not found");
            }
            String email = user.getEmail();

            String token = jwtTokenProvider.createToken(email);

            Map<Object, Object> response = new HashMap<>();
            response.put("username", email);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException error) {
            throw new BadCredentialsException("Wrong username or password", error);
        }
    }

    @PostMapping("signup")
    public ResponseEntity registerUser(@RequestBody SignUpRequestDto signUpRequestDto) {
        String username = signUpRequestDto.getUsername();
        User user = userService.findByUsername(username);

        if (user != null) {
            return new ResponseEntity<>("username already registered", HttpStatus.BAD_REQUEST);
        }

        String email = signUpRequestDto.getEmail();
        user = userService.findByEmail(email);
        if (user == null) {
            return new ResponseEntity<>("email already registered", HttpStatus.BAD_REQUEST);
        }
        if (userService.checkEmail(email)) {
            return new ResponseEntity<>("Wrong email", HttpStatus.BAD_REQUEST);
        }

        User newUser = new User();
        newUser.setUsername(signUpRequestDto.getUsername());
        newUser.setEmail(signUpRequestDto.getEmail());
        newUser.setRole(signUpRequestDto.getRole());
        newUser.setPassword(signUpRequestDto.getPassword());

        userService.register(newUser);
        return new ResponseEntity<>("User registered", HttpStatus.OK);
    }
}