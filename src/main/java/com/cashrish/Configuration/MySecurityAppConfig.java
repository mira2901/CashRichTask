package com.cashrish.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecurityAppConfig {
	
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable(); }
	 */

	 @Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http.csrf().disable();
	        return http.build();
		}
}
