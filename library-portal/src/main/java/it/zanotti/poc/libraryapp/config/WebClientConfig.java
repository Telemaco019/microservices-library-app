package it.zanotti.poc.libraryapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Michele Zanotti on 31/12/20
 **/
@Configuration
public class WebClientConfig {
    @Value("${engine.url}")
    private String engineUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(engineUrl)
                .build();
    }
}
