package it.zanotti.poc.libraryapp.apigateway.books;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Michele Zanotti on 27/12/20
 **/
@Component
@ConfigurationProperties(prefix = "services.book.destinations")
public class BookDestinations {
    private String catalogueServiceUrl;
    private String catalogueSearchServiceUrl;

    public String getCatalogueServiceUrl() {
        return catalogueServiceUrl;
    }

    public String getCatalogueSearchServiceUrl() {
        return catalogueSearchServiceUrl;
    }

    public void setCatalogueServiceUrl(String catalogueServiceUrl) {
        this.catalogueServiceUrl = catalogueServiceUrl;
    }

    public void setCatalogueSearchServiceUrl(String catalogueSearchServiceUrl) {
        this.catalogueSearchServiceUrl = catalogueSearchServiceUrl;
    }
}
