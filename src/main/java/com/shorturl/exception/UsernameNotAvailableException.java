package com.shorturl.exception;

public class UsernameNotAvailableException extends RuntimeException {
	public UsernameNotAvailableException(String message) {
		super(message);
	}

	public UsernameNotAvailableException(String message, Throwable cause) {
		super(message, cause);
	}
}
