package com.shorturl.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class UrlResponse {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	final int expiry_date = 7;

	private final UUID id;	// profile id - foreign key

	private String key;
	private String link;

	private String created_at;
	private String updated_at;
	private String expires_at; // placeholder for now

	private Integer clicked;

	public UrlResponse(UUID id, String key, String link) {
		this.id = id;
		this.key = key;
		this.link = link;
		this.created_at = LocalDateTime.now().format(formatter);
		this.updated_at = created_at;
		this.expires_at = LocalDateTime.parse(created_at).plusDays(expiry_date).toString();
		this.clicked = 0;
	}

	public UUID getId() {
		return id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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
