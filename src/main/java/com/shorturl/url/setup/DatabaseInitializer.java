package com.shorturl.url.setup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseInitializer {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

	private final JdbcTemplate jdbcTemplate;

	public DatabaseInitializer(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@PostConstruct
	public void init() {
		setup();
	}

	public void setup() {
		try {
			jdbcTemplate.execute(
					"CREATE TABLE IF NOT EXISTS urls ("
							+ " id SERIAL PRIMARY KEY, "
							+ " username TEXT, "
							+ " token TEXT NOT NULL UNIQUE, "
							+ " long_url TEXT NOT NULL, "
							+ " created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
							+ " updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
							+ " expires_at TIMESTAMP, "
							+ " clicked BIGINT DEFAULT 0 "
							+ ")");
		} catch (DataAccessException e) {
			logger.debug(e.getMessage());
		}
	}
}