package it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.services;

import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Michele Zanotti on 24/12/20
 **/
public interface BookRepository extends CrudRepository<Book, Integer> {
    Page<Book> findAll(Pageable pageable);
}
