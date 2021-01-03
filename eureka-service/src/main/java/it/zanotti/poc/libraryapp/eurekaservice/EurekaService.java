package it.zanotti.poc.libraryapp.eurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Michele Zanotti on 03/01/21
 **/
@EnableEurekaServer
@SpringBootApplication
public class EurekaService {
    public static void main(String[] args) {
        SpringApplication.run(EurekaService.class);
    }
}
