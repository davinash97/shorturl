package com.shorturl.service;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shorturl.core.Base62Service;
import com.shorturl.exception.InvalidTokenException;
import com.shorturl.repository.UrlRepository;

@Service
public class UrlService {

	// private static final Logger logger = LoggerFactory.getLogger(UrlService.class);
	public final Base62Service base62Service;
	public final UrlRepository urlRepository;

	public static final AtomicLong idSequence = new AtomicLong(1);

	public UrlService(Base62Service base62Service, UrlRepository urlRepository) {
		this.base62Service = base62Service;
		this.urlRepository = urlRepository;
	}

	@Transactional
	public String encodeLongUrl(String longUrl) {
		if(longUrl == null || longUrl.isBlank()) {
				throw new IllegalArgumentException("Url cannot be blank");
		}
		Long generatedId = idSequence.getAndIncrement();
		String token = Base62Service.encode(generatedId);
		return urlRepository.insertOne(token, longUrl);
	}

	public String getLongUrl(String token) {
		Long id = base62Service.decode(token);
		String longUrl = urlRepository.findOne(id);

		if(longUrl == null) {
			throw new InvalidTokenException("the link doesn't exist");
		}

		return longUrl;
	}
}