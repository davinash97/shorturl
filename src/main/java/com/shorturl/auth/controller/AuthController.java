package com.shorturl.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shorturl.common.model.ApiResponse;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

	@GetMapping("/login/{key}")
	public ResponseEntity<ApiResponse<String>> welcomeByPathVar(@PathVariable String key) {
		return ResponseEntity.ok(new ApiResponse<>(
				HttpStatus.OK.value(),
				HttpStatus.OK.getReasonPhrase(),
				"Welcome " + key));
	}

	@GetMapping("/login")
	public ResponseEntity<ApiResponse<String>> welcomeByParam(@RequestParam String key) {
		return ResponseEntity.ok(new ApiResponse<>(
				HttpStatus.OK.value(),
				HttpStatus.OK.getReasonPhrase(),
				"Welcome " + key));
	}
}
