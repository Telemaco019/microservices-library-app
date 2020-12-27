package it.zanotti.poc.microservices.libraryapp.cataloguesearchservice.api;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


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

