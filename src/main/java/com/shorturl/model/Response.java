package com.shorturl.model;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class Response {

	private final int http_code;
	private final String status;
	private String body;
	private String short_url;
	private String long_url;

	
	public Response(int http_code, String status, String body) {
		this.http_code = http_code;
		this.status = status;
		this.body = body;
	}

	public Response(int http_code, String status, String short_url, String long_url) {
		this.http_code = http_code;
		this.status = status.toLowerCase();
		this.short_url = short_url;
		this.long_url = long_url;
	}

	public int getHttpCode() {
		return http_code;
	}

	public String getStatus() {
		return status;
	}

	public String getShortUrl() {
		return short_url;
	}

	public String getLongUrl() {
		return long_url;
	}

	public String getBody() {
		return body;
	}

}
