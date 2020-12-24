package it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.services;

import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.model.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Michele Zanotti on 24/12/20
 **/
public interface AuthorRepository extends CrudRepository<Author, Integer> {
    List<Author> findAll(Pageable page);
}
