package com.shorturl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shorturl.model.Response;
import com.shorturl.service.UrlService;

@RestController
@RequestMapping("/")
public class UrlController {

	@Autowired
	public UrlService UrlService;

	@GetMapping
	public Response getUrl() {
		return  (UrlService.getUrl())
				? new Response(HttpStatus.OK.value(), HttpStatus.OK.name(), "hey! there this is URL Shortener")
				: new Response(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), "some error occured");
	}
}