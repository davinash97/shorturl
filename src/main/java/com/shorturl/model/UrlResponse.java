package com.shorturl.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class UrlResponse {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	final int expiry_date = 7;

	private final UUID id;	// profile id - foreign token

	private String token;
	private String long_url;

	private String created_at;
	private String updated_at;
	private String expires_at; // placeholder for now

	private Integer clicked;

	public UrlResponse(UUID id, String token, String long_url) {
		this.id = id;
		this.token = token;
		this.long_url = long_url;
		this.created_at = LocalDateTime.now().format(formatter);
		this.updated_at = created_at;
		this.expires_at = LocalDateTime.parse(created_at).plusDays(expiry_date).toString();
		this.clicked = 0;
	}

	public UUID getId() {
		return id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLongUrl() {
		return long_url;
	}

	public void setLongUrl(String long_url) {
		this.long_url = long_url;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getExpires_at() {
		return expires_at;
	}

	public void setExpires_at(String expires_at) {
		this.expires_at = expires_at;
	}

	public Integer getClicked() {
		return clicked;
	}

	public void setClicked(Integer clicked) {
		this.clicked = clicked;
	}

}
