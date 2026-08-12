package com.shorturl.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "file://${user.dir}/.env")
public class Config {

	@Value("${server.port}")
	private int PORT;

	@Value("${spring.datasource.url}")
	private String SPRING_DATASOURCE_URL;

	@Value("${spring.datasource.username}")
	private String SPRING_DATASOURCE_USERNAME;

	@Value("${spring.datasource.password}")
	private String SPRING_DATASOURCE_PASSWORD;

	public int getPort() {
		return PORT;
	}

	public String getDbUrl() {
		return SPRING_DATASOURCE_URL;
	}

	public String getDbUsername() {
		return SPRING_DATASOURCE_USERNAME;
	}

	public String getDbPassword() {
		return SPRING_DATASOURCE_PASSWORD;
	}

}
