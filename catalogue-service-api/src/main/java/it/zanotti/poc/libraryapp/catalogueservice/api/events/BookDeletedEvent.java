package it.zanotti.poc.libraryapp.catalogueservice.api.events;

import lombok.Data;

/**
 * @author Michele Zanotti on 25/12/20
 **/
@Data
public class BookDeletedEvent implements BookDomainEvent {
    private Integer bookId;

    public BookDeletedEvent() {

    }

    public BookDeletedEvent(Integer bookId) {
        this.bookId = bookId;
    }
}
