package it.zanotti.poc.microservices.libraryapp.cataloguesearchservice.adapters.rest;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Michele Zanotti on 27/12/20
 **/
@Data
public class SearchedBook {
    private Integer id;
    private String title;
    private String subtitle;
    private String description;
    private List<String> authors;
    private LocalDate publicationDate;
}
