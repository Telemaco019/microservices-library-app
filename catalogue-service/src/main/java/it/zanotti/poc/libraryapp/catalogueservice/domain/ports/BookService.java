package it.zanotti.poc.libraryapp.catalogueservice.domain.ports;

import it.zanotti.poc.libraryapp.catalogueservice.api.web.CreateBookReq;
import it.zanotti.poc.libraryapp.catalogueservice.api.web.UpdateBookReq;
import it.zanotti.poc.libraryapp.catalogueservice.domain.model.Book;
import it.zanotti.poc.libraryapp.catalogueservice.domain.model.exceptions.BookNotFoundException;

import java.util.List;

/**
 * @author Michele Zanotti on 26/12/20
 **/
public interface BookService {
    Book deleteBook(Integer bookId) throws BookNotFoundException;

    Book createBook(CreateBookReq req);

    Book updateBook(UpdateBookReq req) throws BookNotFoundException;

    List<Book> getBooks(Integer limit, Integer offset);
}
