package it.zanotti.poc.libraryapp.catalogueservice.domain.model;

import it.zanotti.poc.libraryapp.catalogueservice.api.events.BookCreatedEvent;
import it.zanotti.poc.libraryapp.catalogueservice.api.events.BookDeletedEvent;
import it.zanotti.poc.libraryapp.catalogueservice.api.events.BookDomainEvent;
import it.zanotti.poc.libraryapp.commons.events.publisher.ResultWithDomainEvents;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
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
    private String subtitle;
    private Integer pages;
    private String publisher;
    private String description;
    private LocalDate publicationDate;
    @ManyToMany(targetEntity = Author.class, cascade = CascadeType.MERGE)
    private List<Author> authors;

    protected Book() {

    }

    public List<BookDomainEvent> deleteBook() {
        return Collections.singletonList(new BookDeletedEvent(id));
    }

    public static class BookBuilder {

        private final Book book;

        private BookBuilder(Book book) {
            this.book = book;
        }

        public static BookBuilder newBook() {
            return new BookBuilder(new Book());
        }

        public BookBuilder withTitle(String title) {
            book.setTitle(title);
            return this;
        }

        public BookBuilder withDescription(String description) {
            book.setDescription(description);
            return this;
        }

        public BookBuilder withSubtitle(String subtitle) {
            book.setSubtitle(subtitle);
            return this;
        }

        public BookBuilder withPages(Integer pages) {
            book.setPages(pages);
            return this;
        }

        public BookBuilder withPublisher(String publisher) {
            book.setPublisher(publisher);
            return this;
        }

        public BookBuilder withPublicationdDate(LocalDate publishedDate) {
            book.setPublicationDate(publishedDate);
            return this;
        }

        public BookBuilder withAuthors(List<Author> authors) {
            book.setAuthors(authors);
            return this;
        }

        public ResultWithDomainEvents<Book, BookDomainEvent> buildBook() {
            final List<String> authors = book.getAuthors().stream().map(Author::getName).collect(Collectors.toList());
            final BookCreatedEvent bookCreatedEvent = new BookCreatedEvent();
            bookCreatedEvent.setBookTitle(book.getTitle());
            bookCreatedEvent.setDescription(book.getDescription());
            bookCreatedEvent.setPages(book.getPages());
            bookCreatedEvent.setPublishedDate(book.getPublicationDate());
            bookCreatedEvent.setSubtitle(book.getSubtitle());
            bookCreatedEvent.setPublisher(book.getPublisher());
            bookCreatedEvent.setAuthors(authors);
            return new ResultWithDomainEvents<>(book, Collections.singletonList(bookCreatedEvent));
        }

    }
}
