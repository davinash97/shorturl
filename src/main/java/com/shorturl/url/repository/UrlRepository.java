package com.shorturl.url.repository;

public interface  UrlRepository {
	public String findOne(String key);
	public String insertOne(String key, String url);
}
