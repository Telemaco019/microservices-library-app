package it.zanotti.poc.microservices.libraryapp.cataloguesearchservice.messaging;

import it.zanotti.poc.microservices.libraryapp.commons.AppConsts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @author Michele Zanotti on 25/12/20
 **/
@Slf4j
@Service
public class BookEventsConsumer {
    @KafkaListener(topics = AppConsts.TOPIC_BOOKS, containerFactory = "kafkaListenerContainerFactory")
    public void test(@Payload String messageContent) {
        log.info("Received message: {}", messageContent);
    }
}
