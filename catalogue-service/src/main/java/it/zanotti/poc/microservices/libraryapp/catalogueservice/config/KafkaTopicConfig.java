package it.zanotti.poc.microservices.libraryapp.catalogueservice.config;

import it.zanotti.poc.microservices.libraryapp.commons.AppConsts;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;

/**
 * @author Michele Zanotti on 25/12/20
 **/
@Configuration
public class KafkaTopicConfig {
    private final KafkaProperties kafkaProperties;

    @Autowired
    public KafkaTopicConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        return new KafkaAdmin(new HashMap<>(kafkaProperties.buildAdminProperties()));
    }

    @Bean
    public NewTopic booksTopic() {
        return new NewTopic(AppConsts.TOPIC_BOOKS, 1, (short) 1);
    }
}
