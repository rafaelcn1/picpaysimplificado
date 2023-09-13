package com.picpaysimplificado.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	/**
	 * Cria uma instância de RestTemplate para realizar chamadas HTTP.
	 * 
	 * @return A instância de RestTemplate.
	 */
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
