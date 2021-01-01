package it.zanotti.poc.libraryapp.apigateway.books;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

/**
 * @author Michele Zanotti on 27/12/20
 **/
@Configuration
public class BookConfiguration {
    @Bean
    public RouteLocator bookProxyRouting(RouteLocatorBuilder builder, BookDestinations bookDestinations) {
        return builder.routes()
                .route(r -> r.path("/books")
                        .and()
                        .method(HttpMethod.PUT, HttpMethod.POST)
                        .uri(bookDestinations.getCatalogueServiceUrl()))
                .route(r -> r.path("/books/searchByText")
                        .and()
                        .method(HttpMethod.PUT)
                        .uri(bookDestinations.getCatalogueSearchServiceUrl()))
                .route(r -> r.path("/books/searchByText/count")
                        .and()
                        .method(HttpMethod.PUT)
                        .uri(bookDestinations.getCatalogueSearchServiceUrl()))
                .route(r -> r.path("/books/{bookId}")
                        .and()
                        .method(HttpMethod.GET)
                        .uri(bookDestinations.getCatalogueServiceUrl()))
                .build();
    }
}
