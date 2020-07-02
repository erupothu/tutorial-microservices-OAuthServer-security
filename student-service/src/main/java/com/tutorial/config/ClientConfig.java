package com.tutorial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
public class ClientConfig {
	
	@Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
