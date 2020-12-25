package it.zanotti.poc.microservices.libraryapp.cataloguesearchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Michele Zanotti on 25/12/20
 **/
@SpringBootApplication
public class CatalogueSearchService {
    public static void main(String[] args) {
        SpringApplication.run(CatalogueSearchService.class, args);
    }
}
