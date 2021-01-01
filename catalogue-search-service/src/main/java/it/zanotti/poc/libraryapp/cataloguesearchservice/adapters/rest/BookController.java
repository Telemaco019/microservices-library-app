package it.zanotti.poc.libraryapp.cataloguesearchservice.adapters.rest;

import it.zanotti.poc.libraryapp.cataloguesearchservice.adapters.converters.BookDocumentConverter;
import it.zanotti.poc.libraryapp.cataloguesearchservice.api.SearchBooksByTextReq;
import it.zanotti.poc.libraryapp.cataloguesearchservice.api.SearchedBook;
import it.zanotti.poc.libraryapp.cataloguesearchservice.domain.ports.BookSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Michele Zanotti on 27/12/20
 **/
@Slf4j
@RestController
public class BookController {
    private final BookSearchService bookSearchService;
    private final BookDocumentConverter bookDocumentConverter;

    @Autowired
    public BookController(BookSearchService bookSearchService, BookDocumentConverter bookDocumentConverter) {
        this.bookSearchService = bookSearchService;
        this.bookDocumentConverter = bookDocumentConverter;
    }

    @PutMapping("/books/searchByText")
    public ResponseEntity<List<SearchedBook>> searchByText(@Valid @RequestBody SearchBooksByTextReq req) {
        final List<SearchedBook> searchedBookList = bookSearchService.searchByText(req.getText(), req.getLimit(), req.getOffset())
                .stream()
                .map(bookDocumentConverter::toSearchedBook)
                .collect(Collectors.toList());
        return new ResponseEntity<>(searchedBookList, HttpStatus.OK);
    }

    @PutMapping("/books/searchByText/count")
    public ResponseEntity<Integer> searchByTextCount(@Valid @RequestBody String text) {
        final Integer count = bookSearchService.searchByTextCount(text);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
