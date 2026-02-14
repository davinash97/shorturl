package com.shorturl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	private static final Logger logger = LoggerFactory.getLogger(UrlController.class);

	@Autowired
	private UrlService urlService;

	@PostMapping
	public Response getUrl(@RequestParam String url) {
		try {
			String shortUrl = urlService.longToShort(url);
			String longUrl = urlService.shortToLong(shortUrl);
			logger.debug("{} generated", shortUrl);
			return (longUrl.equals(url))
					? new Response(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), shortUrl, longUrl)
					: new Response(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		} catch (Exception e) {
			logger.error("error occured at {}", e.getMessage());
			return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
	}
	
	@GetMapping("{key}")
	public Object sendUrl(@PathVariable String key) {
		try {
			logger.debug("requested key {}", key);
			String result = urlService.shortToLong(key);
			return (result != null) ? new RedirectView(result)
			: new Response(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		} catch (Exception e) {
			logger.error("error occured at {}", e.getMessage());
			return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
	}

}