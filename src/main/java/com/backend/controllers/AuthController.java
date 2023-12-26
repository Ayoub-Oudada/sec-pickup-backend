package com.backend.controllers;

import com.backend.dtos.requests.AuthenticationRequest;
import com.backend.entities.User;
import com.backend.entities.UserAccountType;
import com.backend.repositories.UsersRepository;
import com.backend.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public void register() {
        usersRepository.save(User.builder()
                .username("ayoub@test.com")
                .password(passwordEncoder.encode("pass"))
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authService.authenticate(authenticationRequest));
    }
}
