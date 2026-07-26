package com.shorturl.url.repository.impl;

import java.util.HashMap;
import java.util.Map;

import com.shorturl.url.repository.UrlRepository;

public class UrlRepositoryMemory implements UrlRepository {

	private final Map<String, String> store = new HashMap<>();

	@Override
	public String insertOne(String id, String url) {
		if( id == null || url == null
			|| id.isBlank() || url.isBlank()) {
			return null;
		}
		store.put(id, url);
		return id;
	}

	@Override
	public String findOne(String id) {
		if(id == null || id.isBlank()) {
			return null;
		}
		return store.getOrDefault(id, null);
	}
}