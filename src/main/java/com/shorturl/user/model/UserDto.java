package com.shorturl.user.model;

public record UserDto(
		String first_name,
		String last_name,
		String password
	) {
}
