package com.shorturl.url.service;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shorturl.url.core.Base62Service;
import com.shorturl.url.exception.InvalidTokenException;
import com.shorturl.url.repository.UrlRepository;

@Service
public class UrlService {

	private static final Logger logger = LoggerFactory.getLogger(UrlService.class);
	public final UrlRepository urlRepository;

	public static final AtomicLong idSequence = new AtomicLong(1);

	public UrlService(UrlRepository urlRepository) {
		this.urlRepository = urlRepository;
	}

	@Transactional
	public String encodeLongUrl(String longUrl) {
		if (longUrl == null || longUrl.isBlank()) {
			return null;
		}
		try {
			Long generatedId = idSequence.getAndIncrement();
			String token = Base62Service.encode(generatedId);
			return urlRepository.insertOne(token, longUrl);
		} catch (InvalidTokenException e) {
			logger.debug(e.getMessage());
			return null;
		}
	}

	public String getLongUrl(String token) {
		String longUrl = urlRepository.findOne(token);
		return longUrl;
	}
}
