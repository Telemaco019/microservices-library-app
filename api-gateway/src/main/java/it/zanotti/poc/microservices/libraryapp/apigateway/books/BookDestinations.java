package it.zanotti.poc.microservices.libraryapp.apigateway.books;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Michele Zanotti on 27/12/20
 **/
@Component
@ConfigurationProperties(prefix = "services.book.destinations")
public class BookDestinations {
    private String bookServiceUrl;
    private String bookSearchServiceUrl;

    public String getBookServiceUrl() {
        return bookServiceUrl;
    }

    public String getBookSearchServiceUrl() {
        return bookSearchServiceUrl;
    }

    public void setBookServiceUrl(String bookServiceUrl) {
        this.bookServiceUrl = bookServiceUrl;
    }

    public void setBookSearchServiceUrl(String bookSearchServiceUrl) {
        this.bookSearchServiceUrl = bookSearchServiceUrl;
    }
}
