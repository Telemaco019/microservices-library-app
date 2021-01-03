package it.zanotti.poc.libraryapp.cataloguesearchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Michele Zanotti on 25/12/20
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class CatalogueSearchService {
    public static void main(String[] args) {
        SpringApplication.run(CatalogueSearchService.class, args);
    }
}
