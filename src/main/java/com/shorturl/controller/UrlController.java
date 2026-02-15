package com.shorturl.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shorturl.model.Response;
import com.shorturl.service.UrlService;

import jakarta.validation.constraints.NotEmpty;

@RestController
@RequestMapping
public class UrlController {

	private final UrlService urlService;

	public UrlController(UrlService urlService) {
		this.urlService = urlService;
	}

	@PostMapping
	public ResponseEntity<Response> createShortUrl(
			@RequestParam @NotEmpty String url) {

		if (!url.matches("https?://.*")) {
			return ResponseEntity.badRequest()
					.body(new Response(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
		}
		String shortUrl = urlService.longToShort(url);

		return ResponseEntity.ok(
				new Response(
						HttpStatus.OK.value(),
						HttpStatus.OK.getReasonPhrase(),
						shortUrl,
						url));
	}

	@GetMapping("{key}")
	public ResponseEntity<?> redirect(@PathVariable String key) {

		String result = urlService.shortToLong(key);

		if (result == null) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new Response(
							HttpStatus.NOT_FOUND.value(),
							HttpStatus.NOT_FOUND.getReasonPhrase()));
		}

		return ResponseEntity
				.status(HttpStatus.FOUND)
				.location(URI.create(result))
				.build();
	}
}
