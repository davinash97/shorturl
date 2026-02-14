package com.shorturl.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "file://${user.dir}/.env")
public class UrlConfig {

	@Value("${server.port}")
	private int PORT;

}
