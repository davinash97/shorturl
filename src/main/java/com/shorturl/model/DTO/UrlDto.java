package com.shorturl.model.DTO;

import java.time.LocalDateTime;

public class UrlDto {

	private final String shortKey;
	private final String longUrl;
	private final LocalDateTime createdAt;

	public UrlDto(String shortKey, String longUrl, LocalDateTime createdAt) {
		this.shortKey = shortKey;
		this.longUrl = longUrl;
		this.createdAt = createdAt;
	}

	public String getShortKey() {
		return shortKey;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

}
