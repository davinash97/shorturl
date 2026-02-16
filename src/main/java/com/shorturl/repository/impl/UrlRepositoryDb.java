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
                        "id VARCHAR(255) PRIMARY KEY, " +
                        "url TEXT NOT NULL" +
                        ")");
    }

    @Override
    public String findOne(String key) {
        logger.debug("Fetching URL from DB for [{}]", key);

        return jdbcTemplate.queryForObject(
                "SELECT url FROM urls WHERE id = ?",
                String.class,
                key);
    }

    @Override
    public String insertOne(String key, String url) {
        setup();
        logger.debug("Inserting [{}] -> [{}] in DB", key, url);
        jdbcTemplate.update("INSERT INTO urls (id, url) VALUES (?, ?)", key, url);
        return key;
    }

    @Override
    public boolean keyExists(String key) {
        logger.debug("Checking if [{}] exists in DB", key);

        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM urls WHERE id = ?",
                Integer.class,
                key);

        return count != null && count > 0;
    }

}
