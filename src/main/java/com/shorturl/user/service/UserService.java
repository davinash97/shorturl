package com.shorturl.user.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.shorturl.user.model.User;
import com.shorturl.user.repository.UserRepository;

@Service
public class UserService {

	protected UserRepository userRepository;

	protected UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User createProfile(UUID id, String username, String first_name, String last_name) {
		return userRepository.save(
				new User(id, username, first_name, last_name));
	}

	public User getProfile(String username) {
		return userRepository.getByUsername(username).orElse(null);
	}

	public User updateProfile(String username, String first_name, String last_name) {
		User user = getProfile(username);
		User newUser = null;

		if (user != null) {
			newUser = new User(user.getId(), username, first_name, last_name);
			userRepository.save(newUser);
		}
		return newUser;
	}

	public User deleteProfile(String username) {
		User user = getProfile(username);
		userRepository.deleteById(user.getId());

		return getProfile(username);
	}
}
