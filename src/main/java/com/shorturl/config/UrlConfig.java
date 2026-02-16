package com.shorturl.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "file://${user.dir}/.env")
public class UrlConfig {

	@Value("${server.port}")
	private int PORT;

	@Value("${spring.datasource.url}")
	private String DB_URL;

	@Value("${spring.datasource.username}")
	private String DB_USERNAME;

	@Value("${spring.datasource.password}")
	private String DB_PASSWORD;

	public int getPort() {
		return PORT;
	}

	public String getDbUrl() {
		return DB_URL;
	}

	public String getDbUsername() {
		return DB_USERNAME;
	}

	public String getDbPassword() {
		return DB_PASSWORD;
	}

}
