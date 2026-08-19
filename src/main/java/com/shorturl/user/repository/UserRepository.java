package com.shorturl.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shorturl.user.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	public Optional<User> getByUsername(String username);
}
