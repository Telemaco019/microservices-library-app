package it.zanotti.poc.microservices.libraryapp.commons.events.publisher;

import it.zanotti.poc.microservices.libraryapp.commons.events.common.DomainEvent;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

/**
 * @author Michele Zanotti on 26/12/20
 **/
public class DomainEventPublisherServiceImpl implements DomainEventPublisherService {
    final KafkaTemplate<String, Object> kafkaTemplate;

    public DomainEventPublisherServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(String topicName, Object aggregateId, List<DomainEvent> domainEvents) {
        for (DomainEvent event : domainEvents) {
            kafkaTemplate.send(
                    topicName, // topic
                    String.valueOf(aggregateId), // key
                    event // payload
            );
        }
    }
}
