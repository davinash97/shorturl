package com.shorturl.common.exception;

public class UsernameAlreadyExistsException extends RuntimeException {

	public UsernameAlreadyExistsException(String username) {
		super("Username already exists: " + username);
	}
}
