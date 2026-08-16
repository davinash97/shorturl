package com.shorturl.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shorturl.auth.exception.EmptyFieldException;
import com.shorturl.auth.exception.UsernameNotAvailableException;
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
		try {
			if (body.getUsername() == null || body.getUsername().isBlank()
					|| body.getPassword() == null || body.getPassword().isBlank()) {

				StringBuilder emptyField = new StringBuilder("Empty Field(s): ");

				if (body.getUsername().isEmpty()) {
					emptyField.append("username");
				}

				if (body.getPassword().isEmpty()) {
					emptyField.append("password");
				}
				throw new EmptyFieldException(emptyField.toString());
			}

			Boolean isProfileCreated = authService.createProfile(body.getUsername(), body.getPassword());

			if (isProfileCreated) {
				return ResponseEntity.status(HttpStatus.CREATED).body(
						new ApiResponse<>(
								HttpStatus.CREATED.value(),
								HttpStatus.CREATED.getReasonPhrase(),
								"account created"));
			}
			throw new Exception("internal server error");

		} catch (EmptyFieldException e) {
			return ResponseEntity.ok(new ApiResponse<>(
					HttpStatus.NOT_ACCEPTABLE.value(),
					HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(),
					e.getMessage()));

		} catch (UsernameNotAvailableException e) {
			return ResponseEntity.ok(new ApiResponse<>(
					HttpStatus.CONFLICT.value(),
					HttpStatus.CONFLICT.getReasonPhrase(),
					body.getUsername() + " already exists"));

		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(new ApiResponse<>(
					HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST.getReasonPhrase(),
					e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponse<>(
					HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
					e.getMessage()));
		}
	}

	// Login
	@GetMapping
	public ResponseEntity<ApiResponse<String>> login(@RequestBody AuthRequest body) {
		try {
			if (body.getUsername() == null || body.getUsername().isBlank()
					|| body.getPassword() == null || body.getPassword().isBlank()) {
				throw new EmptyFieldException("Empty fields");
			}

			String token = authService.login(body.getUsername(), body.getPassword());

			if (token == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
						new ApiResponse<>(
								HttpStatus.UNAUTHORIZED.value(),
								HttpStatus.UNAUTHORIZED.getReasonPhrase(),
								"invalid credentials"));
			}

			return ResponseEntity.ok(
					new ApiResponse<>(
							HttpStatus.OK.value(),
							HttpStatus.OK.getReasonPhrase(),
							token));
		} catch (EmptyFieldException e) {
			return ResponseEntity.ok(new ApiResponse<>(
					HttpStatus.NOT_ACCEPTABLE.value(),
					HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(),
					e.getMessage()));

		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(new ApiResponse<>(
					HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST.getReasonPhrase(),
					e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponse<>(
					HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
					e.getMessage()));
		}
	}
}
