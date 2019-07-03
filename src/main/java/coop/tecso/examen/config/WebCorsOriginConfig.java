package coop.tecso.examen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCorsOriginConfig implements WebMvcConfigurer {

	public WebCorsOriginConfig() {
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("PUT", "GET", "POST", "DELETE")
				.exposedHeaders(HttpHeaders.AUTHORIZATION);
	}

}