package com.backend.exceptionhandlers.auth;

import com.backend.dtos.responses.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthenticationExceptionsHandler {
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<AuthenticationResponse> handler(InternalAuthenticationServiceException e) {
        return new ResponseEntity<>(
                AuthenticationResponse
                        .builder()
                        .errors("Bad credentials")
                        .build(),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<AuthenticationResponse> handler(BadCredentialsException e) {
        return new ResponseEntity<>(
                AuthenticationResponse
                        .builder()
                        .errors(e.getMessage())
                        .build(),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<AuthenticationResponse> handler(UsernameNotFoundException e) {
        return new ResponseEntity<>(
                AuthenticationResponse
                        .builder()
                        .errors(e.getMessage())
                        .build(),
                HttpStatus.FORBIDDEN
        );
    }
}
