package it.zanotti.poc.microservices.libraryapp.cataloguesearchservice.adapters;

import it.zanotti.poc.microservices.libraryapp.cataloguesearchservice.adapters.converters.BookDocumentConverter;
import it.zanotti.poc.microservices.libraryapp.cataloguesearchservice.domain.model.BookDocument;
import it.zanotti.poc.microservices.libraryapp.cataloguesearchservice.domain.ports.SolrBookRepository;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.api.events.BookCreatedEvent;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.api.events.BookDeletedEvent;
import it.zanotti.poc.microservices.libraryapp.commons.AppConsts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final SolrBookRepository solrBookRepository;
    private final BookDocumentConverter bookDocumentConverter;

    @Autowired
    public BookEventsConsumer(SolrBookRepository solrBookRepository, BookDocumentConverter bookDocumentConverter) {
        this.solrBookRepository = solrBookRepository;
        this.bookDocumentConverter = bookDocumentConverter;
    }

    @KafkaHandler
    public void handleBookCreated(@Payload BookCreatedEvent bookCreatedEvent,
                                  @Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String messageKey,
                                  @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
                                  @Header(value = KafkaHeaders.RECEIVED_PARTITION_ID) Integer partitionId,
                                  @Header(value = KafkaHeaders.GROUP_ID) String groupId) {
        logMessageReceived(topic, groupId, partitionId);
        final BookDocument bookDocument = bookDocumentConverter.toBookDocument(Integer.valueOf(messageKey), bookCreatedEvent);
        log.info("Saving in Solr book document [id: {}]", messageKey);
        solrBookRepository.save(bookDocument);
    }

    @KafkaHandler
    public void handleBookDeleted(@Payload BookDeletedEvent bookDeletedEvent,
                                  @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
                                  @Header(value = KafkaHeaders.RECEIVED_PARTITION_ID) Integer partitionId,
                                  @Header(value = KafkaHeaders.GROUP_ID) String groupId) {
        logMessageReceived(topic, groupId, partitionId);
        log.info("Deleting from Solr book document [id: {}]", bookDeletedEvent.getBookId());
        solrBookRepository.deleteById(bookDeletedEvent.getBookId());
    }

    @KafkaHandler(isDefault = true)
    public void handleDefault(Object object) {
        log.warn("Cannot handle message: {}", object.toString());
    }

    private void logMessageReceived(String topic, String groupId, Integer partitionId) {
        log.debug("Received message [topic {}, group {}, partition {}]", topic, groupId, partitionId);
    }
}
