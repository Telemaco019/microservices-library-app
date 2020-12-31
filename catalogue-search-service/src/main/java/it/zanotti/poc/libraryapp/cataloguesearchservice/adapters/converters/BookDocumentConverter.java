package it.zanotti.poc.libraryapp.cataloguesearchservice.adapters.converters;

import it.zanotti.poc.libraryapp.cataloguesearchservice.api.SearchedBook;
import it.zanotti.poc.libraryapp.cataloguesearchservice.domain.model.BookDocument;
import it.zanotti.poc.libraryapp.catalogueservice.api.events.BookCreatedEvent;
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

    public SearchedBook toSearchedBook(BookDocument bookDocument) {
        final SearchedBook result = new SearchedBook();
        result.setAuthors(bookDocument.getAuthors());
        result.setDescription(bookDocument.getDescription());
        result.setSubtitle(bookDocument.getSubtitle());
        result.setTitle(bookDocument.getTitle());
        result.setId(bookDocument.getId());
        return result;
    }
}
