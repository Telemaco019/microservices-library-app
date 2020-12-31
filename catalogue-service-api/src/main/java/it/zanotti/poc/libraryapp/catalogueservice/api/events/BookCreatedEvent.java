package it.zanotti.poc.libraryapp.catalogueservice.api.events;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Michele Zanotti on 25/12/20
 **/
@Data
public class BookCreatedEvent implements BookDomainEvent {
    private String bookTitle;
    private List<String> authors;
    private String subtitle;
    private Integer pages;
    private String publisher;
    private String description;
    private LocalDate publishedDate;
}
