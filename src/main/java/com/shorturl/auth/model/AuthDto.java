package com.shorturl.auth.model;

import java.util.UUID;

public interface AuthDto {
	UUID getId();

	String getUsername();

	String getPassword();
}
