package it.zanotti.poc.libraryapp.cataloguesearchservice.domain.ports;

import it.zanotti.poc.libraryapp.cataloguesearchservice.domain.model.BookDocument;
import it.zanotti.poc.libraryapp.commons.OffsetBasedPageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    public List<BookDocument> searchByText(String text, Integer limit, Integer offset) {
        final OffsetBasedPageRequest pageRequest = new OffsetBasedPageRequest(limit, offset);
        final Page<BookDocument> page = bookRepository.findByTextContent(text, pageRequest);
        return page.getContent();
    }
}
