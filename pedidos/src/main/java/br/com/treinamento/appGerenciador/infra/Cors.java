package br.com.treinamento.appGerenciador.infra;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class Cors {
	public class CorsConfiguration implements WebMvcConfigurer {

		@Override
		public void addCorsMappings(CorsRegistry registry) {

			registry.addMapping("/**")
					.allowedOrigins("http://127.0.0.1:5500")
					.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
					.allowedHeaders("*")
					.allowCredentials(true);
		}
	}
}
