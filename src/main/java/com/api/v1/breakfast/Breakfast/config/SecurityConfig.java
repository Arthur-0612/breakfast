package com.api.v1.breakfast.Breakfast.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		CorsConfiguration cors = new CorsConfiguration();
		cors.addAllowedHeader("*");
		cors.addAllowedMethod("*");
		cors.setAllowCredentials(true);
		cors.addAllowedOrigin("*");

		source.registerCorsConfiguration("/**", cors);

		return source;
	}
}
