package it.zanotti.poc.microservices.libraryapp.cataloguesearchservice.adapters;

import it.zanotti.poc.microservices.libraryapp.cataloguesearchservice.domain.model.BookDocument;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.api.events.BookCreatedEvent;
import org.springframework.stereotype.Component;

/**
 * @author Michele Zanotti on 26/12/20
 **/
@Component
public class BookDocumentConverter {
    public BookDocument toBookDocument(Integer bookId, BookCreatedEvent bookCreatedEvent) {
        final BookDocument result = new BookDocument();
        result.setId(bookId);
        result.setAuthors(bookCreatedEvent.getAuthors());
        result.setDescription(bookCreatedEvent.getDescription());
        result.setTitle(bookCreatedEvent.getBookTitle());
        result.setSubtitle(bookCreatedEvent.getSubtitle());
        return result;
    }
}
