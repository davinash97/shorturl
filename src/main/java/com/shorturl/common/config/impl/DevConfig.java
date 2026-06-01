package com.shorturl.common.config.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.shorturl.common.config.Config;

@Configuration
@Profile("dev")
@PropertySource(value = "file://${user.dir}/dev.env")
public class DevConfig implements Config{

	@Value("${server.port}")
	private int PORT;

	@Value("${spring.datasource.url}")
	private String DB_URL;

	@Value("${spring.datasource.username}")
	private String DB_USERNAME;

	@Value("${spring.datasource.password}")
	private String DB_PASSWORD;

	@Override
	public int getPort() {
		return PORT;
	}

	@Override
	public String getDbUrl() {
		return DB_URL;
	}

	@Override
	public String getDbUsername() {
		return DB_USERNAME;
	}

	@Override
	public String getDbPassword() {
		return DB_PASSWORD;
	}

}
