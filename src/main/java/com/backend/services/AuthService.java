package com.backend.services;

import com.backend.dtos.requests.AuthenticationRequest;
import com.backend.dtos.responses.AuthenticationResponse;
import com.backend.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationProvider authenticationProvider;
    private final UsersRepository usersRepository;
    private final JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        var user = usersRepository.findUserByUsernameAndType(
                authenticationRequest.getUsername(),
                authenticationRequest.getType()
        ).orElseThrow(() -> new UsernameNotFoundException("user not found!"));

        return AuthenticationResponse
                .builder()
                .token(jwtService.generateToken(user))
                .build();
    }
}
