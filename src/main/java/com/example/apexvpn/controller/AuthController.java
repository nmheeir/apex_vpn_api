package com.example.apexvpn.controller;

import com.example.apexvpn.entities.User;
import com.example.apexvpn.repositories.UserRepository;
import com.example.apexvpn.responses.TokenResponse;
import com.example.apexvpn.services.AuthService;
import com.example.apexvpn.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password
    ) {
        return authService.login(username, password);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "role") String role
    ) {
        return authService.register(username, email, password, role);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout successful");
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<Object> refreshToken(
            @RequestParam(name = "refreshToken") String token
    ) {
        if (jwtUtils.isTokenValid(token)) {
            String username = jwtUtils.extractUsername(token);
            User user = userRepository.findUserByUsername(username);

            String newAccessToken = jwtUtils.generateAccessToken(user);
            String newRefreshToken = jwtUtils.generateRefreshToken(null, user);

            return TokenResponse.tokenResponseBuilder("Successfully", newAccessToken, newRefreshToken);
        }
        return TokenResponse.tokenResponseBuilder("Can't refresh token", null, null);
    }


    @PostMapping("/verify")
    public ResponseEntity<Object> verifyEmail(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "code") String code
    ) {
        return authService.verifyEmail(email, code);
    }

    @PostMapping("/isUsernameEmailExist")
    public ResponseEntity<Object> checkUsernameEmail(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "email") String email
    ) {
        return authService.checkUsernameEmail(username, email);
    }

    @PostMapping("/isEmailExist")
    public ResponseEntity<Object> checkEmail(
            @RequestParam(name = "email") String email
    ) {
        return authService.checkEmail(email);
    }
}
