package com.shorturl.repository;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
public class UrlRepository {

	private static final Logger logger = LoggerFactory.getLogger(UrlRepository.class);

	private static final Map<String, String> map = new HashMap<>();
	
	@Profile("dev")
	public String findOne(String key) {
		logger.debug("getting url for [{}]", key);
		return map.get(key);
	}
	
	@Profile("dev")
	public String insertOne(String key, String url) {
		logger.debug("adding [{}] for [{}]", key, url);
		map.put(key, url);
		return key;
	}
	
	@Profile("dev")
	public boolean keyExists(String key) {
		logger.debug("checking if [{}] exists", key);
		return map.containsKey(key);
	}

}
