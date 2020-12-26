package it.zanotti.poc.microservices.libraryapp.catalogueservice.services;

import it.zanotti.poc.microservices.libraryapp.catalogueservice.api.web.CreateOrUpdateBookReq;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.exceptions.BookNotFoundException;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.model.Book;

import java.util.List;

/**
 * @author Michele Zanotti on 26/12/20
 **/
public interface BookService {
    Book deleteBook(Integer bookId) throws BookNotFoundException;

    Book createBook(CreateOrUpdateBookReq req);

    List<Book> getBooks(Integer limit, Integer offset);
}
