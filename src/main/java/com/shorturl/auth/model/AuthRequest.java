package com.shorturl.auth.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "auth", uniqueConstraints = @UniqueConstraint(name = "uq_username", columnNames = { "username" }))
public class AuthRequest {

	protected AuthRequest() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	public AuthRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
