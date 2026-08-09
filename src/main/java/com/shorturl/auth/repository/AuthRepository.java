package com.shorturl.auth.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.shorturl.user.model.User;

public interface AuthRepository extends CrudRepository<User, UUID> {
	boolean existsByUsername(String username);
}