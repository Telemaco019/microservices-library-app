package it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.model;

import it.zanotti.poc.microservices.libraryapp.catalogueservice.api.events.BookCreatedEvent;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.api.events.BookDeletedEvent;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.api.events.BookDomainEvent;
import it.zanotti.poc.microservices.libraryapp.commons.events.ResultWithDomainEvents;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Michele Zanotti on 24/12/20
 **/
@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @ManyToMany(targetEntity = Author.class, cascade = CascadeType.MERGE)
    private List<Author> authors;

    private Book() {

    }

    public Book(String title, List<Author> authors) {
        this.title = title;
        this.authors = authors;
    }

    public static ResultWithDomainEvents<Book, BookCreatedEvent> createBook(String title, List<Author> authors) {
        final BookCreatedEvent bookCreatedEvent = new BookCreatedEvent(
                title,
                authors.stream().map(Author::getName).collect(Collectors.toList())
        );
        return new ResultWithDomainEvents<>(
                new Book(title, authors),
                Collections.singletonList(bookCreatedEvent)
        );
    }

    public List<BookDomainEvent> deleteBook() {
        return Collections.singletonList(new BookDeletedEvent(id));
    }
}
