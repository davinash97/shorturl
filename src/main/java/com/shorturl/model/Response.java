package com.shorturl.model;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class Response {

	private final int http_code;
	private final String status;
	private final String body;

	public Response(int http_code, String status, String body) {
		this.http_code = http_code;
		this.status = status.toLowerCase();
		this.body = body;
	}

	public int getHttpCode() {
		return http_code;
	}

	public String getStatus() {
		return status;
	}

	public String getBody() {
		return body;
	}

}
