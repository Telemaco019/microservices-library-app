package it.zanotti.poc.microservices.libraryapp.cataloguesearchservice.domain.ports;

import it.zanotti.poc.microservices.libraryapp.cataloguesearchservice.domain.model.BookDocument;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 * @author Michele Zanotti on 26/12/20
 **/
public interface SolrBookRepository extends SolrCrudRepository<BookDocument, Integer> {
}
