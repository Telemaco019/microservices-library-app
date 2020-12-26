package it.zanotti.poc.microservices.libraryapp.cataloguesearchservice.messaging;

import it.zanotti.poc.microservices.libraryapp.catalogueservice.api.events.BookCreatedEvent;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.api.events.BookDeletedEvent;
import it.zanotti.poc.microservices.libraryapp.commons.AppConsts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @author Michele Zanotti on 25/12/20
 **/
@Slf4j
@Service
@KafkaListener(topics = AppConsts.TOPIC_BOOKS, containerFactory = "jsonKafkaListenerContainerFactory")
public class BookEventsConsumer {
    @KafkaHandler
    public void handleBookCreated(@Payload BookCreatedEvent bookCreatedEvent,
                                  @Header(value = KafkaHeaders.MESSAGE_KEY, required = false) String messageKey,
                                  @Header(KafkaHeaders.GROUP_ID) String groupId) {
        log.info("Received message (consumer group {}): {}", groupId, bookCreatedEvent);
    }

    @KafkaHandler
    public void handleBookDeleted(@Payload BookDeletedEvent bookCreatedEvent) {
        log.info("Received message: {}", bookCreatedEvent);
    }

    @KafkaHandler(isDefault = true)
    public void handleDefault(Object object) {
        log.warn("Cannot handle message: {}", object.toString());
    }
}
