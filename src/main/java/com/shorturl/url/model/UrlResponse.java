package com.shorturl.url.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public record UrlResponse(
		UUID id,
		String token, String long_url,
		String created_at, String updated_at,
		String expires_at) {

	private static final DateTimeFormatter FORMATTER
			= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static UrlResponse from(
			UUID id,
			String token, String longUrl,
			LocalDateTime createdAt, LocalDateTime updatedAt,
			LocalDateTime expiresAt) {

		return new UrlResponse(
				id,
				token, longUrl, createdAt != null ? createdAt.format(FORMATTER) : null,
				updatedAt != null ? updatedAt.format(FORMATTER) : null,
				expiresAt != null ? expiresAt.format(FORMATTER) : null
		);
	}
}
