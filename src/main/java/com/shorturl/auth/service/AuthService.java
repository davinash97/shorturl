package com.shorturl.auth.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shorturl.auth.model.User;
import com.shorturl.auth.repository.AuthRepository;

import jakarta.validation.constraints.NotNull;

@Service
public class AuthService {

	@Autowired
	private final AuthRepository authRepository;

	public AuthService(AuthRepository authRepository) {
		this.authRepository = authRepository;
	}

	@Transactional
	public Boolean createProfile(@NotNull String username, @NotNull String password,
			@NotNull String first_name, @NotNull String last_name) {
		if (authRepository.existsByUsername(username)) {
			throw new IllegalArgumentException("username already exists");
		}
		User result = authRepository.save(new User(username, password, first_name, last_name));
		return result != null;
	}

	public User readProfile(UUID id) {
		return authRepository.findById(id).orElse(null);
	}
}
