package com.shorturl.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shorturl.model.ApiResponse;
import com.shorturl.model.DTO.UrlDto;
import com.shorturl.service.UrlService;

import jakarta.validation.constraints.NotEmpty;

@RestController
@RequestMapping
public class UrlController {

	private static final Logger logger = LoggerFactory.getLogger(UrlController.class);
	private final UrlService urlService;

	public UrlController(UrlService urlService) {
		this.urlService = urlService;
	}

	@PostMapping("/api/v1")
	public ResponseEntity<ApiResponse> createShortUrl(
			@RequestParam @NotEmpty String url) {

		if (!url.matches("https?://.*")) {
			return ResponseEntity.badRequest()
					.body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),
							HttpStatus.BAD_REQUEST.getReasonPhrase(), "bad request"));
		}

		try {

			String token = urlService.encodeLongUrl(url);
			return ResponseEntity.ok(
					new ApiResponse<>(
							HttpStatus.OK.value(),
							HttpStatus.OK.getReasonPhrase(),
							new UrlDto(token, url)));
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
					.body(new ApiResponse<>(
							HttpStatus.INTERNAL_SERVER_ERROR.value(),
							HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
							null));
		}
	}

	@GetMapping("/{token}")
	public ResponseEntity<?> redirect(@PathVariable @NotEmpty String token) {

		try {
			String result = urlService.getLongUrl(token);
			if (result == null) {
				return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body(new ApiResponse<>(
								HttpStatus.NOT_FOUND.value(),
								HttpStatus.NOT_FOUND.getReasonPhrase(),
								"Url not found with token" + token));
			}

			return ResponseEntity
					.status(HttpStatus.FOUND)
					.location(URI.create(result))
					.build();
		} catch (Exception e) {
			logger.debug("error occured at: " + e.getMessage());
			return ResponseEntity.internalServerError()
					.body(new ApiResponse<>(
							HttpStatus.INTERNAL_SERVER_ERROR.value(),
							HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
							null));
		}
	}
}
