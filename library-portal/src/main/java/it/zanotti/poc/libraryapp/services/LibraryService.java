package it.zanotti.poc.libraryapp.services;

import it.zanotti.poc.libraryapp.model.BookSearchResult;
import it.zanotti.poc.libraryapp.model.SearchByTextRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Michele Zanotti on 31/12/20
 **/
@Slf4j
@Service
public class LibraryService {
    private final WebClient webClient;

    @Autowired
    public LibraryService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<BookSearchResult> searchByText(String text, Integer limit, Integer offset) {
        return webClient.put()
                .uri("/books/searchByText")
                .body(BodyInserters.fromValue(new SearchByTextRequest(text, limit, offset)))
                .retrieve()
                .bodyToFlux(BookSearchResult.class)
                .doOnError(e -> log.error("Error searching by text: {}", e.getMessage(), e))
                .onErrorStop();
    }

    public Mono<Integer> searchByTextCount(String searchedText) {
        return webClient.put()
                .uri("/books/searchByText/count")
                .body(BodyInserters.fromValue(searchedText))
                .retrieve()
                .bodyToMono(Integer.class)
                .doOnError(e -> log.error("Error searching by text: {}", e.getMessage(), e));
    }
}
