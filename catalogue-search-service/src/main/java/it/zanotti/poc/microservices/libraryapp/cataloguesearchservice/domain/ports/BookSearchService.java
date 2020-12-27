package it.zanotti.poc.microservices.libraryapp.cataloguesearchservice.domain.ports;

import it.zanotti.poc.microservices.libraryapp.cataloguesearchservice.domain.model.BookDocument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Michele Zanotti on 27/12/20
 **/
@Slf4j
@Service
public class BookSearchService {
    private final SolrBookRepository bookRepository;

    @Autowired
    public BookSearchService(SolrBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDocument> searchByText(String text) {
        return bookRepository.findByTitleContainingOrDescriptionContainingOrSubtitleContaining(text);
    }
}
