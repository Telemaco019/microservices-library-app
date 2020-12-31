package it.zanotti.poc.libraryapp.catalogueservice.domain.ports;

import it.zanotti.poc.libraryapp.catalogueservice.adapters.BookDomainEventPublisher;
import it.zanotti.poc.libraryapp.catalogueservice.api.events.BookDomainEvent;
import it.zanotti.poc.libraryapp.catalogueservice.api.web.CreateBookReq;
import it.zanotti.poc.libraryapp.catalogueservice.api.web.UpdateBookReq;
import it.zanotti.poc.libraryapp.catalogueservice.domain.model.Author;
import it.zanotti.poc.libraryapp.catalogueservice.domain.model.Book;
import it.zanotti.poc.libraryapp.catalogueservice.domain.model.exceptions.BookNotFoundException;
import it.zanotti.poc.libraryapp.commons.OffsetBasedPageRequest;
import it.zanotti.poc.libraryapp.commons.events.publisher.ResultWithDomainEvents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional
    public Book deleteBook(Integer bookId) throws BookNotFoundException {
        final Book bookToDelete = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        final List<BookDomainEvent> bookDomainEvents = bookToDelete.deleteBook();
        eventPublisher.publish(bookToDelete, bookDomainEvents);
        return bookToDelete;
    }

    @Override
    @Transactional
    public Book createBook(CreateBookReq req) {
        final ResultWithDomainEvents<Book, BookDomainEvent> bookAndEvents = Book.BookBuilder.newBook()
                .withAuthors(
                        req.getAuthors()
                                .stream()
                                .map(Author::new)
                                .collect(Collectors.toList())
                )
                .withTitle(req.getTitle())
                .withDescription(req.getDescription())
                .withPublishedDate(req.getPublishedDate())
                .withSubtitle(req.getSubtitle())
                .withPages(req.getPages())
                .withPublisher(req.getPublisher())
                .buildBook();
        final Book savedBook = bookRepository.save(bookAndEvents.getResult());
        final List<BookDomainEvent> events = bookAndEvents.getEvents();
        eventPublisher.publish(savedBook, events);
        return savedBook;
    }

    @Override
    public Book updateBook(UpdateBookReq req) throws BookNotFoundException {
        return null;
    }

    @Override
    public List<Book> getBooks(Integer limit, Integer offset) {
        final OffsetBasedPageRequest page = new OffsetBasedPageRequest(offset, limit);
        final Page<Book> result = bookRepository.findAll(page);
        return result.getContent();
    }
}
