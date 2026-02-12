package com.shorturl.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.shorturl.model.Response;
import com.shorturl.service.UrlService;

@RestController
@RequestMapping("/")
public class UrlController {

	@PostMapping
	public Response getUrl(@RequestParam String url) {
		try {
			String shortUrl = UrlService.longToShort(url);
			String longUrl = UrlService.shortToLong(shortUrl);
			return new Response(HttpStatus.OK.value(), HttpStatus.OK.name(), shortUrl, longUrl);
		} catch (Exception e) {
			return new Response(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), "some error occured");
		}
	}

	@GetMapping("{key}")
	public RedirectView sendUrl(@PathVariable String key) {
		return new RedirectView(UrlService.shortToLong(key));
	}
}