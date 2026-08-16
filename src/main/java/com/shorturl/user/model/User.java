package com.shorturl.user.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "uq_username", columnNames = { "username" }))
public class User {

	protected User() {
	};

	@Id
	@Column(nullable = false)
	private UUID id;

	@Column(nullable = false)
	private String first_name;

	@Column(nullable = false)
	private String last_name;

	@Column(nullable = false)
	private String username;

	public User(UUID id, String username, String first_name, String last_name) {
		this.id = id;
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
	}

	public UUID getId() {
		return id;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
