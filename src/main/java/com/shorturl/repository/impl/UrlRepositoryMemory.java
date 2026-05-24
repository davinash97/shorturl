package com.shorturl.repository.impl;

import java.util.HashMap;
import java.util.Map;

import com.shorturl.repository.UrlRepository;

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
		return store.get(id);
	}
}