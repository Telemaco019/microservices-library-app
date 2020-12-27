package it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.ports;

import it.zanotti.poc.microservices.libraryapp.catalogueservice.api.web.CreateOrUpdateBookReq;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.model.Book;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.model.exceptions.BookNotFoundException;

import java.util.List;

/**
 * @author Michele Zanotti on 26/12/20
 **/
public interface BookService {
    Book deleteBook(Integer bookId) throws BookNotFoundException;

    Book createBook(CreateOrUpdateBookReq req);

    List<Book> getBooks(Integer limit, Integer offset);
}
