package it.zanotti.poc.microservices.libraryapp.catalogueservice.controllers;

import it.zanotti.poc.microservices.libraryapp.catalogueservice.controllers.requests.CreateOrUpdateAuthorReq;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.controllers.requests.GetAuthorsReq;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.model.Author;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.services.AuthorRepository;
import it.zanotti.poc.microservices.libraryapp.catalogueservice.utils.OffsetBasedPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Michele Zanotti on 24/12/20
 **/
@RestController
public class AuthorController {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostMapping("/api/v1/authors")
    public ResponseEntity<Author> createOrUpdateAuthor(@RequestBody CreateOrUpdateAuthorReq req) {
        final Author author = new Author();
        author.setId(req.getAuthorId());
        author.setName(req.getAuthorName());

        return new ResponseEntity<>(authorRepository.save(author), HttpStatus.OK);
    }

    @GetMapping("/api/v1/authors")
    public ResponseEntity<List<Author>> getAuthors(@RequestBody GetAuthorsReq req) {
        final Integer limit = req.getLimit();
        final Integer offset = req.getOffset();
        final OffsetBasedPageRequest pageRequest = new OffsetBasedPageRequest(offset, limit);
        return new ResponseEntity<>(authorRepository.findAll(pageRequest), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/authors/{authorId}")
    public ResponseEntity<Boolean> deleteAuthor(@PathVariable Integer authorId) {
        authorRepository.deleteById(authorId);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}
