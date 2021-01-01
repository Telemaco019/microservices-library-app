package it.zanotti.poc.libraryapp.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Michele Zanotti on 31/12/20
 **/
@Configuration
public class GsonConfig {
    @Bean
    public Gson gson() {
        return new GsonBuilder().create();
    }
}
