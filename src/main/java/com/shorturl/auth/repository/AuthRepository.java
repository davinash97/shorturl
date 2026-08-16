package com.shorturl.auth.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shorturl.auth.model.AuthDto;
import com.shorturl.auth.model.AuthRequest;

public interface AuthRepository extends JpaRepository<AuthRequest, UUID> {
	boolean existsByUsername(String username);

	Optional<AuthRequest> findByUsername(String username);

	Optional<AuthDto> findIdByUsername(String username);
}