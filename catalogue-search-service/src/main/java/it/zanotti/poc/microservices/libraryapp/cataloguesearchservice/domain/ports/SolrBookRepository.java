package it.zanotti.poc.microservices.libraryapp.cataloguesearchservice.domain.ports;

import it.zanotti.poc.microservices.libraryapp.cataloguesearchservice.domain.model.BookDocument;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

/**
 * @author Michele Zanotti on 26/12/20
 **/
public interface SolrBookRepository extends SolrCrudRepository<BookDocument, Integer> {
    List<BookDocument> findByTitleContainingOrDescriptionContainingOrSubtitleContaining(String text);
}
