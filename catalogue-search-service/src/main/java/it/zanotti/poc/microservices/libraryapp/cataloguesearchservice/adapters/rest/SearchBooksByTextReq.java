package it.zanotti.poc.microservices.libraryapp.cataloguesearchservice.adapters.rest;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Michele Zanotti on 27/12/20
 **/
@Data
public class SearchBooksByTextReq {
    private String text;
    @NotNull
    private Integer limit;
    @NotNull
    private Integer offset;
}

