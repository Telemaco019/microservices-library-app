package it.zanotti.poc.libraryapp.catalogueservice.adapters;

import it.zanotti.poc.libraryapp.catalogueservice.api.events.BookDomainEvent;
import it.zanotti.poc.libraryapp.catalogueservice.domain.model.Book;
import it.zanotti.poc.libraryapp.commons.AppConsts;
import it.zanotti.poc.libraryapp.commons.events.publisher.DomainEventPublisher;
import it.zanotti.poc.libraryapp.commons.events.publisher.DomainEventPublisherService;
import org.springframework.stereotype.Service;

/**
 * @author Michele Zanotti on 26/12/20
 **/
@Service
public class BookDomainEventPublisher extends DomainEventPublisher<Book, BookDomainEvent> {
    protected BookDomainEventPublisher(DomainEventPublisherService publisherService) {
        super(publisherService, AppConsts.TOPIC_BOOKS, Book::getId);
    }
}
