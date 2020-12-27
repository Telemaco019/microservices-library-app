package it.zanotti.poc.microservices.libraryapp.catalogueservice.api.web;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Michele Zanotti on 27/12/20
 **/
@Data
public class UpdateAuthorReq {
    @NotNull
    private Integer id;
    private String authorName;
}
