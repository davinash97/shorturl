package com.shorturl.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.shorturl.repository.UrlRepository;

@Repository
@Profile("prod")
public class UrlRepositoryDb implements UrlRepository {

    private static final Logger logger = LoggerFactory.getLogger(UrlRepositoryDb.class);

    @Override
    public String findOne(String key) {
        logger.debug("Fetching URL from DB for [{}]", key);
        // to-do implement
        return null;
    }

    @Override
    public String insertOne(String key, String url) {
        logger.debug("Inserting [{}] -> [{}] in DB", key, url);
        // to-do implement
        return null;
    }

    @Override
    public boolean keyExists(String key) {
        logger.debug("Checking if [{}] exists in DB", key);
        return false; // to-do implement
    }
}
