package com.shorturl.service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shorturl.core.UrlCore;

@Service
public class UrlService {

	private static final SecureRandom random = new SecureRandom();

	private static Map<String, String> map = new HashMap<>();

	public static String longToShort(String url) {
		String key;
		do {
			long id = random.nextLong(10_000_000L, 100_000_000L);
			key = UrlCore.encode(id);
		} while (map.containsKey(key));

		map.put(key, url);
		return key;
	}

	// Implement (if key not exist)
	public static String shortToLong(String key) {
		return map.get(key);
	}

}