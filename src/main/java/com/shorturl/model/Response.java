package com.shorturl.model;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class Response {

	private final int code;
	private final String status;
	private String short_url;
	private String long_url;

	
	public Response(int code, String status) {
		this.code = code;
		this.status = status;
	}

	public Response(int code, String status, String short_url, String long_url) {
		this.code = code;
		this.status = status.toLowerCase();
		this.short_url = short_url;
		this.long_url = long_url;
	}

	public int getCode() {
		return code;
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

}
