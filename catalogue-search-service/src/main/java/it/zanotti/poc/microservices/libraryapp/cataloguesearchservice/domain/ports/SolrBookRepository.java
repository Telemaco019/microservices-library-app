package it.zanotti.poc.microservices.libraryapp.cataloguesearchservice.domain.ports;

import it.zanotti.poc.microservices.libraryapp.cataloguesearchservice.domain.model.BookDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 * @author Michele Zanotti on 26/12/20
 **/
public interface SolrBookRepository extends SolrCrudRepository<BookDocument, Integer> {
    @Query("title:?0 OR description:?0 OR subtitle:?0")
    Page<BookDocument> findByTextContent(String text, Pageable page);
}
