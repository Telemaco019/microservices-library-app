package it.zanotti.poc.microservices.libraryapp.catalogueservice.api.events;

import lombok.Data;

import java.util.List;

/**
 * @author Michele Zanotti on 25/12/20
 **/
@Data
public class BookCreatedEvent implements BookDomainEvent {
    private String bookTitle;
    private List<String> authors;

    public BookCreatedEvent() {

    }

    public BookCreatedEvent(String bookTitle, List<String> authors) {
        this.bookTitle = bookTitle;
        this.authors = authors;
    }
}
