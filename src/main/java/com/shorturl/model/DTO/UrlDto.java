package com.shorturl.model.DTO;

public class UrlDto {

	private final String token;
	private final String longUrl;

	public UrlDto(String token, String longUrl) {
		this.token = token;
		this.longUrl = longUrl;
	}

	public String getId() {
		return token;
	}

	public String getLongUrl() {
		return longUrl;
	}

}
