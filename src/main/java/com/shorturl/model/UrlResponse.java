package com.shorturl.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class UrlResponse {

	private final String id;

	private String userId;

	private String shortKey;
	private String longUrl;

	LocalDateTime createdAt;
	LocalDateTime expiresAt; // Hardcoding 7 days for now

	// private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

	// System.out.println("Created: " + createdAt.format(formatter));
	// System.out.println("Expires: " + expiresAt.format(formatter));

	public UrlResponse(String userId,
						String shortKey, String longUrl) {
		this.id = UUID.randomUUID().toString();
		this.userId = userId;
		this.shortKey = shortKey;
		this.longUrl = longUrl;
		this.createdAt = LocalDateTime.now() ;
		this.expiresAt = createdAt.plusDays(7);
	}

	public String getId() {
		return id;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getShortKey() {
		return shortKey;
	}

	public void setShortKey(String shortKey) {
		this.shortKey = shortKey;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

}