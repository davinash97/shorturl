package com.shorturl.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shorturl.auth.exception.EmptyFieldException;
import com.shorturl.auth.exception.InvalidTokenException;
import com.shorturl.auth.model.AuthRequest;
import com.shorturl.auth.service.AuthService;
import com.shorturl.common.model.ApiResponse;

@RestController
@RequestMapping("/api/v1/auth")
class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Sign Up
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createAccount(@RequestBody AuthRequest body) {
        if (body.getUsername() == null || body.getUsername().isBlank() || body.getPassword() == null || body.getPassword().isBlank()) {

            StringBuilder emptyField = new StringBuilder("Empty Field(s): ");

            if (body.getUsername().isEmpty()) {
                emptyField.append("username");
            }

            if (body.getPassword().isEmpty()) {
                emptyField.append("password");
            }
            throw new EmptyFieldException(emptyField.toString());
        }

        authService.createProfile(body.getUsername(), body.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), "account created"));
    }


    // Login
    @GetMapping
    public ResponseEntity<ApiResponse<String>> login(@RequestBody AuthRequest body) {
        if (body.getUsername() == null || body.getUsername().isBlank() || body.getPassword() == null || body.getPassword().isBlank()) {
            StringBuilder emptyField = new StringBuilder("Empty Field(s): ");

            if (body.getUsername().isEmpty()) {
                emptyField.append("username");
            }

            if (body.getPassword().isEmpty()) {
                emptyField.append("password");
            }
            throw new EmptyFieldException(emptyField.toString());
        }

        String token = authService.login(body.getUsername(), body.getPassword());

        if (token == null) {
            throw new InvalidTokenException("invalid credentials");
        }

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), token));
    }
}
