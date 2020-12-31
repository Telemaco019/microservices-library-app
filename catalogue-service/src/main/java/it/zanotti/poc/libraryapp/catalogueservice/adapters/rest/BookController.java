package it.zanotti.poc.libraryapp.catalogueservice.adapters.rest;

import it.zanotti.poc.libraryapp.catalogueservice.api.web.CreateBookReq;
import it.zanotti.poc.libraryapp.catalogueservice.api.web.UpdateBookReq;
import it.zanotti.poc.libraryapp.catalogueservice.domain.model.Book;
import it.zanotti.poc.libraryapp.catalogueservice.domain.model.exceptions.BookNotFoundException;
import it.zanotti.poc.libraryapp.catalogueservice.domain.ports.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Michele Zanotti on 24/12/20
 **/
@RestController
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@Valid @RequestBody CreateBookReq request) {
        final Book savedBook = bookService.createBook(request);
        return new ResponseEntity<>(savedBook, HttpStatus.OK);
    }

    @PutMapping("/books")
    public ResponseEntity<Book> updateBook(@Valid @RequestBody UpdateBookReq request) {
        final Book savedBook;
        try {
            savedBook = bookService.updateBook(request);
            return new ResponseEntity<>(savedBook, HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookService.getBooks(0, 10);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Book> deleteBook(@PathVariable Integer bookId) {
        try {
            final Book deletedBook = bookService.deleteBook(bookId);
            return new ResponseEntity<>(deletedBook, HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
