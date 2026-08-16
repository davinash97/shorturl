package com.shorturl.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shorturl.auth.exception.UsernameNotAvailableException;
import com.shorturl.auth.model.AuthDto;
import com.shorturl.auth.model.AuthRequest;
import com.shorturl.auth.repository.AuthRepository;

import jakarta.validation.constraints.NotNull;

@Service
public class AuthService {

	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

	private AuthRepository authRepository;

	private JwtService jwtService;

	private PasswordEncoder passwordEncoder;

	public AuthService(AuthRepository authRepository,
			PasswordEncoder passwordEncoder,
			JwtService jwtService) {
		this.authRepository = authRepository;
		this.jwtService = jwtService;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public Boolean createProfile(
			@NotNull String username,
			@NotNull String password) throws UsernameNotAvailableException {

		if (authRepository.existsByUsername(username)) {
			throw new UsernameNotAvailableException(username + " already exists");
		}

		String encodedPassword = passwordEncoder.encode(password);

		AuthRequest result = authRepository.save(
				new AuthRequest(username, encodedPassword));

		logger.debug("Profile created [{}]", result.getUsername());

		return result != null;
	}

	public String login(@NotNull String username, @NotNull String password) {
		AuthRequest user = readProfile(username);
		if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
			return null;
		}
		return jwtService.generateToken(username);
	}

	public AuthRequest readProfile(@NotNull String username) {
		return authRepository.findByUsername(username).orElse(null);
	}

	public AuthDto getProfile(@NotNull String username) {
		return authRepository.findIdByUsername(username).orElse(null);
	}
}
