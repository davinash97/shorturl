package com.shorturl.model;

import java.util.List;
import java.util.UUID;

public class Profile {

	private final UUID id;

	private String first_name;
	private String last_name;
	private String username;

	private String password;

	private List<UrlResponse> urls;

	public Profile(String first_name, String last_name, String username, String password) {
		this.id = UUID.randomUUID();
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
	}

	public UUID getId() {
		return id;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UrlResponse> getUrls() {
		return urls;
	}

	public void setUrls(List<UrlResponse> urls) {
		this.urls = urls;
	}

}
