package it.zanotti.poc.microservices.libraryapp.catalogueservice.services;

import it.zanotti.poc.microservices.libraryapp.catalogueservice.api.events.BookDomainEvent;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.BookDomainEventPublisher;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.exceptions.BookNotFoundException;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.model.Author;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.model.Book;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.repositories.BookRepository;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.utils.OffsetBasedPageRequest;
import it.zanotti.poc.microservices.libraryapp.commons.events.publisher.ResultWithDomainEvents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Michele Zanotti on 26/12/20
 **/
@Slf4j
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookDomainEventPublisher eventPublisher;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookDomainEventPublisher eventPublisher) {
        this.bookRepository = bookRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Book deleteBook(Integer bookId) throws BookNotFoundException {
        final Book bookToDelete = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        final List<BookDomainEvent> bookDomainEvents = bookToDelete.deleteBook();
        eventPublisher.publish(bookToDelete, bookDomainEvents);
        return bookToDelete;
    }

    @Override
    public Book createBook(String bookTitle, List<Author> bookAuthors) {
        final ResultWithDomainEvents<Book, BookDomainEvent> bookAndEvents = Book.createBook(bookTitle, bookAuthors);
        final Book savedBook = bookRepository.save(bookAndEvents.getResult());
        final List<BookDomainEvent> events = bookAndEvents.getEvents();
        eventPublisher.publish(savedBook, events);
        return savedBook;
    }

    @Override
    public List<Book> getBooks(Integer limit, Integer offset) {
        final OffsetBasedPageRequest page = new OffsetBasedPageRequest(offset, limit);
        final Page<Book> result = bookRepository.findAll(page);
        return result.getContent();
    }
}
