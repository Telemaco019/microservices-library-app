package it.zanotti.poc.microservices.libraryapp.catalogueservice.config;

import it.zanotti.poc.microservices.libraryapp.commons.events.publisher.DomainEventPublisherService;
import it.zanotti.poc.microservices.libraryapp.commons.events.publisher.DomainEventPublisherServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author Michele Zanotti on 26/12/20
 **/
@Configuration
public class CatalogueServiceConfiguration {
    @Bean
    public DomainEventPublisherService domainEventPublisherService(KafkaTemplate<String, Object> kafkaTemplate) {
       return new DomainEventPublisherServiceImpl(kafkaTemplate);
    }
}
