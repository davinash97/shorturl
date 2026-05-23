package com.shorturl.repository;

public interface  UrlRepository {
	public String findOne(Long key);
	public String insertOne(String key, String url);
}
