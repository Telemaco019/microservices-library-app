package it.zanotti.poc.libraryapp.catalogueservice.adapters;

import it.zanotti.poc.libraryapp.commons.AppConsts;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author Michele Zanotti on 25/12/20
 **/
@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic booksTopic() {
        return TopicBuilder.name(AppConsts.TOPIC_BOOKS)
                .partitions(5)
                .replicas(1)
                .build();
    }
}
