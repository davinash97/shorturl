package com.shorturl.url.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public interface Config {
	public int getPort();
	public String getDbUrl();
	public String getDbUsername();
	public String getDbPassword();
}
