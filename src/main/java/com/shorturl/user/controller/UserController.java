package com.shorturl.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shorturl.auth.model.AuthDto;
import com.shorturl.auth.service.AuthService;
import com.shorturl.auth.service.JwtService;
import com.shorturl.common.model.ApiResponse;
import com.shorturl.user.model.User;
import com.shorturl.user.model.UserDto;
import com.shorturl.user.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	protected UserService userService;
	protected JwtService jwtService;
	protected AuthService authService;
	protected PasswordEncoder passwordEncoder;

	protected UserController(UserService userService, JwtService jwtService, AuthService authService,
			PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.jwtService = jwtService;
		this.authService = authService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping
	public ResponseEntity<ApiResponse<User>> createProfile(
			@RequestBody UserDto userDto,
			Authentication authentication) {
		String authUser = authentication.getName();

		AuthDto authDto = authService.getProfileByUsername(authUser);

		validateUserForCreate(userDto, authUser, authDto);

		return ResponseEntity.ok(
				new ApiResponse<>(
						HttpStatus.OK.value(),
						HttpStatus.OK.getReasonPhrase(),
						userService.createProfile(
								authDto.getId(),
								authUser,
								userDto.first_name(),
								userDto.last_name())));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<User>> getProfile(Authentication authentication) {
		String authUser = authentication.getName();
		return ResponseEntity.ok(
				new ApiResponse<>(
						HttpStatus.OK.value(),
						HttpStatus.OK.getReasonPhrase(),
						userService.getProfile(authUser)));
	}

	@PutMapping
	public ResponseEntity<ApiResponse<User>> getProfile(@RequestBody UserDto userDto, Authentication authentication) {
		String authUser = authentication.getName();

		AuthDto authDto = authService.getProfileByUsername(authUser);

		User user = userService.getProfile(authUser);

		validateUserForUpdate(user, userDto, authUser, authDto);

		return ResponseEntity.ok(
				new ApiResponse<>(
						HttpStatus.OK.value(),
						HttpStatus.OK.getReasonPhrase(),
						userService.updateProfile(authUser, userDto.first_name(),
								userDto.last_name())));
	}

	@DeleteMapping
	public ResponseEntity<ApiResponse<User>> deleteProfile(@RequestBody UserDto userDto,
			Authentication authentication) {
		String authUser = authentication.getName();
		AuthDto authDto = authService.getProfileByUsername(authUser);

		User user = userService.getProfile(authUser);

		validateUserForUpdate(user, userDto, authUser, authDto);

		return ResponseEntity.ok(
				new ApiResponse<>(
						HttpStatus.OK.value(),
						HttpStatus.OK.getReasonPhrase(),
						userService.deleteProfile(authUser)));
	}

	protected void validateUserForCreate(UserDto userDto, String authUser, AuthDto authDto) {

		// if Authdto is null
		if (authDto == null) {
			logger.debug("[] is null", authUser);
			throw new AccessDeniedException(
					"authUser " + authUser + " is null");
		}

		// if Paswwowrd is empty
		if (authDto.getPassword().isBlank() || authDto.getPassword().isEmpty()) {
			logger.debug("password field is empty");
			throw new AccessDeniedException(
					authUser + " is not authorized to make this change " + authDto + " is empty");
		}

		// if password doesn't match
		if (!passwordEncoder.matches(userDto.password(), authDto.getPassword())) {
			logger.debug("invalid password [] != []", userDto, authDto);
			throw new AccessDeniedException("invalid password");
		}
	}

	protected void validateUserForUpdate(User user, UserDto userDto, String authUser, AuthDto authDto) {

		// if Authdto or user is null
		if (user == null) {
			logger.debug("[] is null", user);
			throw new AccessDeniedException(
					"user " + user + " is null");
		}

		validateUserForCreate(userDto, authUser, authDto);
	}
}