package com.shorturl.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shorturl.model.Response;

@RestController
public class DefaultController {

	@GetMapping
	public ResponseEntity<Response> defaultPath() {

		return ResponseEntity.ok(
				new Response(
						HttpStatus.OK.value(),
						"this is a URL shortener, GET/POST or any type of request is not supported here."));
	}
}
