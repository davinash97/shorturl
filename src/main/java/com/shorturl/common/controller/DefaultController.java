package com.shorturl.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shorturl.url.model.ApiResponse;

@RestController
public class DefaultController {

	@GetMapping
	public ResponseEntity<ApiResponse<String>> defaultPath() {

		return ResponseEntity.ok(
				new ApiResponse<>(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.getReasonPhrase(),
						"this is a URL shortener, GET/POST or any type of request is not supported here."));
	}
}
