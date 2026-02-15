package com.shorturl.service;

import java.security.SecureRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shorturl.core.UrlCore;
import com.shorturl.repository.UrlRepository;

@Service
public class UrlService {

	private static final Logger logger = LoggerFactory.getLogger(UrlService.class);

	private static final SecureRandom random = new SecureRandom();

	private final UrlRepository urlRepository;

	public UrlService(UrlRepository urlRepository) {
		this.urlRepository = urlRepository;
	}

	public String longToShort(String url) {
		if(url == null || url.isBlank()) {
			logger.debug("url is empty");
			return null;
		}
		String key;
		do {
			long id = random.nextLong(10_000_000L, 100_000_000L);
			key = UrlCore.encode(id);
			logger.debug("[{}] generated", key);
		} while (urlRepository.keyExists(key));
		return urlRepository.insertOne(key, url);
	}

	// Implement (if key not exist)
	public String shortToLong(String key) {
		if(key == null || key.isBlank()) {
			logger.debug("key is empty");
			return null;
		}
		logger.debug("checking availability of [{}]", key);
		return (urlRepository.keyExists(key)) ? urlRepository.findOne(key) : null;
	}

}