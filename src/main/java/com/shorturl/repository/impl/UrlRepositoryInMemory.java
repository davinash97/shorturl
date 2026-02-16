package com.shorturl.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.shorturl.repository.UrlRepository;

@Repository
@Profile("dev")
public class UrlRepositoryInMemory implements UrlRepository {

    private static final Logger logger = LoggerFactory.getLogger(UrlRepository.class);

    private static final Map<String, String> map = new HashMap<>();

    @Override
    public String findOne(String key) {
        if (key == null || key.isBlank()) {
            logger.debug("key is empty");
            return null;
        }
        logger.debug("getting url for [{}]", key);
        return map.get(key);
    }

    @Override
    public String insertOne(String key, String url) {
        if (key == null || key.isBlank()
                || url == null || url.isBlank()) {
            logger.debug("key and/or url is empty");
            return null;
        }
        logger.debug("adding [{}] for [{}]", key, url);
        map.put(key, url);
        return key;
    }

    @Override
    public boolean keyExists(String key) {
        if (key == null || key.isBlank()) {
            logger.debug("key is empty");
            return false;
        }
        logger.debug("checking if [{}] exists", key);
        return map.containsKey(key);
    }

}
