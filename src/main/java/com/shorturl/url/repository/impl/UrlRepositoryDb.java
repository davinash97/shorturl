package com.shorturl.url.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shorturl.url.core.Base62Service;
import com.shorturl.url.repository.UrlRepository;
import com.shorturl.url.service.UrlService;

@Repository
public class UrlRepositoryDb implements UrlRepository {

	private static final Logger logger = LoggerFactory.getLogger(UrlRepositoryDb.class);

	private final JdbcTemplate jdbcTemplate;

	public UrlRepositoryDb(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setup() {
		jdbcTemplate.execute(
				"CREATE TABLE IF NOT EXISTS urls ("
				+ " id SERIAL PRIMARY KEY, "
				+ " token TEXT NOT NULL UNIQUE, "
				+ " long_url TEXT NOT NULL, "
				+ " created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
				+ " updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
				+ " expires_at TIMESTAMP, "
				+ " clicked BIGINT DEFAULT 0 "
				+ ")"
		);
	}

	@Override
	public String findOne(String token) {
		logger.debug("Fetching URL from DB for [{}]", token);
		setup();
		String result;
		try {
			result = jdbcTemplate.queryForObject(
					"SELECT long_url FROM urls WHERE token = ?",
					String.class,
					token);
		} catch (EmptyResultDataAccessException e) {
			result = null;
		}
		return result;
	}

	@Override
	@Transactional
	public String insertOne(String token, String long_url) {
		setup();
		logger.debug("Inserting [{}] -> [{}] in DB", token, long_url);

		try {
			String sql = "INSERT INTO urls (token, long_url) VALUES (?, ?) ON CONFLICT (token) DO NOTHING";
			int rowsAffected = jdbcTemplate.update(sql, token, long_url);
			if (rowsAffected > 0) {
				return token;
			}

			logger.debug("Token: [{}] already exists, generating new", token);

			Long generatedId = UrlService.idSequence.getAndIncrement();
			token = Base62Service.encode(generatedId);
			return insertOne(token, long_url);
		} catch (EmptyResultDataAccessException e) {
			logger.debug("error occured at -> InsertOne" + e.getMessage());
			return null;
		}
	}
}
