package com.shorturl.repository;

public interface  UrlRepository {
	public String findOne(String key);
	public String insertOne(String key, String url);
}
