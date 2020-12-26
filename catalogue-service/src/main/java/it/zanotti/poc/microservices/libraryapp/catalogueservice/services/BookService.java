package it.zanotti.poc.microservices.libraryapp.catalogueservice.services;

import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.exceptions.BookNotFoundException;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.model.Author;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.model.Book;

import java.util.List;

/**
 * @author Michele Zanotti on 26/12/20
 **/
public interface BookService {
    Book deleteBook(Integer bookId) throws BookNotFoundException;

    Book createBook(String bookTitle, List<Author> bookAuthors);

    List<Book> getBooks(Integer limit, Integer offset);
}
