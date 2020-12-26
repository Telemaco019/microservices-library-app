package it.zanotti.poc.microservices.libraryapp.catalogueservice.services;

import it.zanotti.poc.microservices.libraryapp.catalogueservice.api.events.BookCreatedEvent;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.api.events.BookDomainEvent;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.exceptions.BookNotFoundException;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.model.Author;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.model.Book;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.services.BookRepository;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.utils.OffsetBasedPageRequest;
import it.zanotti.poc.microservices.libraryapp.commons.AppConsts;
import it.zanotti.poc.microservices.libraryapp.commons.events.ResultWithDomainEvents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Michele Zanotti on 26/12/20
 **/
@Slf4j
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.bookRepository = bookRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Book deleteBook(Integer bookId) throws BookNotFoundException {
        final Book bookToDelete = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        final List<BookDomainEvent> bookDomainEvents = bookToDelete.deleteBook();
        for (BookDomainEvent event : bookDomainEvents) {
            kafkaTemplate.send(AppConsts.TOPIC_BOOKS, String.valueOf(bookId), event);
        }
        return bookToDelete;
    }

    @Override
    public Book createBook(String bookTitle, List<Author> bookAuthors) {
        final ResultWithDomainEvents<Book, BookCreatedEvent> bookAndEvents = Book.createBook(bookTitle, bookAuthors);

        final Book savedBook = bookRepository.save(bookAndEvents.getResult());
        final List<BookCreatedEvent> events = bookAndEvents.getEvents();

        for (BookCreatedEvent event : events) {
            kafkaTemplate.send(AppConsts.TOPIC_BOOKS, String.valueOf(bookAndEvents.getResult().getId()), event);
        }

        return savedBook;
    }

    @Override
    public List<Book> getBooks(Integer limit, Integer offset) {
        final OffsetBasedPageRequest page = new OffsetBasedPageRequest(offset, limit);
        final Page<Book> result = bookRepository.findAll(page);
        return result.getContent();
    }
}
