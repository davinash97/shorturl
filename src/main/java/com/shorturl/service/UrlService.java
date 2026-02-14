package com.shorturl.service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shorturl.core.UrlCore;

@Service
public class UrlService {

	private static final Logger logger = LoggerFactory.getLogger(UrlService.class);

	private static final SecureRandom random = new SecureRandom();

	private static final Map<String, String> map = new HashMap<>();

	public String longToShort(String url) {
		String key;
		do {
			long id = random.nextLong(10_000_000L, 100_000_000L);
			key = UrlCore.encode(id);
			logger.debug("{} generated", key);
		} while (map.containsKey(key));

		map.put(key, url);
		return key;
	}

	// Implement (if key not exist)
	public String shortToLong(String key) {
		logger.debug("accessed {}", key);
		return (map.containsKey(key))? map.get(key): null;
	}

}