package it.zanotti.poc.microservices.libraryapp.catalogueservice.api.events;

import lombok.Data;

import java.util.List;

/**
 * @author Michele Zanotti on 25/12/20
 **/
@Data
public class BookCreatedEvent implements BookDomainEvent {
    private Integer bookId;
    private String bookTitle;
    private List<String> authors;
}
