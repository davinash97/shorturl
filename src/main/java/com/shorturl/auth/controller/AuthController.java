package com.shorturl.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shorturl.auth.model.User;
import com.shorturl.auth.service.AuthService;
import com.shorturl.common.model.ApiResponse;

@RestController
@RequestMapping("/api/v1")
class AuthController {

	@Autowired
	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	// Sign Up
	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<?>> createAccount(@RequestBody User body) {
		if(body.getUsername().isEmpty() || body.getFirst_name().isEmpty()
			|| body.getLast_name().isEmpty() || body.getPassword().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		try {
			
		Boolean result = authService.createProfile(body.getUsername(), body.getPassword(),
									body.getFirst_name(), body.getLast_name());
		return result ? ResponseEntity.accepted().body(
					new ApiResponse<>(
						HttpStatus.ACCEPTED.value(),
						HttpStatus.ACCEPTED.getReasonPhrase(),
						result))
					: ResponseEntity.notFound().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(new ApiResponse<>(
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(),
				body.getUsername() + " already exists"
			));
		}
	}

	// Login 
	@GetMapping("/login")
	public ResponseEntity<ApiResponse<String>> welcomeByParam(@RequestParam String key) {
		return ResponseEntity.ok(new ApiResponse<>(
				HttpStatus.OK.value(),
				HttpStatus.OK.getReasonPhrase(),
				"Welcome " + key));
	}
}
