package it.zanotti.poc.microservices.libraryapp.catalogueservice.controllers;

import it.zanotti.poc.microservices.libraryapp.catalogueservice.controllers.requests.CreateOrUpdateBookReq;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.model.Author;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.model.Book;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.services.BookRepository;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.utils.OffsetBasedPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Michele Zanotti on 24/12/20
 **/
@RestController
public class BookController {
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping("/api/v1/books")
    public ResponseEntity<Book> createOrUpdateBook(@RequestBody CreateOrUpdateBookReq request) {
        final List<Author> authorList = request.getAuthors()
                .stream()
                .map(Author::new)
                .collect(Collectors.toList());
        final Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthors(authorList);

        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.OK);
    }

    @GetMapping("/api/v1/books")
    public ResponseEntity<List<Book>> getBooks() {
        OffsetBasedPageRequest page = new OffsetBasedPageRequest(0, 10);
        Page<Book> result = bookRepository.findAll(page);
        return new ResponseEntity<>(result.getContent(), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/book/{bookId}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable Integer bookId) {
        bookRepository.deleteById(bookId);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}
