package it.zanotti.poc.microservices.libraryapp.catalogueservice.domain;

import it.zanotti.poc.microservices.libraryapp.catalogueservice.api.events.BookDomainEvent;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.model.Book;
import it.zanotti.poc.microservices.libraryapp.commons.events.publisher.DomainEventPublisher;
import it.zanotti.poc.microservices.libraryapp.commons.events.publisher.DomainEventPublisherService;
import org.springframework.stereotype.Service;

/**
 * @author Michele Zanotti on 26/12/20
 **/
@Service
public class BookDomainEventPublisher extends DomainEventPublisher<Book, BookDomainEvent> {
    protected BookDomainEventPublisher(DomainEventPublisherService publisherService) {
        super(publisherService, Book.class, Book::getId);
    }
}
