package com.shorturl.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shorturl.repository.UrlRepository;

@Repository
@Profile("prod")
public class UrlRepositoryDb implements UrlRepository {

	private static final Logger logger = LoggerFactory.getLogger(UrlRepositoryDb.class);
	private final JdbcTemplate jdbcTemplate;

	public UrlRepositoryDb(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setup() {
		jdbcTemplate.execute(
				"CREATE TABLE IF NOT EXISTS urls (" + 
				" id SERIAL PRIMARY KEY, " +
				" key TEXT NOT NULL, " +
				" long_url TEXT NOT NULL, " +
				" created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP " +
				")"
			);
	}

	@Override
	public String findOne(String key) {
		logger.debug("Fetching URL from DB for [{}]", key);
		setup();
		return jdbcTemplate.queryForObject(
				"SELECT long_url FROM urls WHERE key = ?",
				String.class,
				key);
	}

	@Override
	public String insertOne(String key, String url) {
		setup();
		logger.debug("Inserting [{}] -> [{}] in DB", key, url);
		jdbcTemplate.update("INSERT INTO urls (key, long_url) VALUES (?, ?)", key, url);
		return key;
	}

	@Override
	public boolean keyExists(String key) {
		setup();
		logger.debug("Checking if [{}] exists in DB", key);

		Integer count = jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM urls WHERE key = ?",
				Integer.class,
				key);

		return count != null && count > 0;
	}
}
