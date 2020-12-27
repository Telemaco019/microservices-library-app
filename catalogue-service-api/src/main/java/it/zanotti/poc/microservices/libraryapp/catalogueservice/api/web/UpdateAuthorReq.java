package it.zanotti.poc.microservices.libraryapp.catalogueservice.api.web;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Michele Zanotti on 27/12/20
 **/
@Data
public class UpdateAuthorReq {
    @NotNull
    private Integer id;
    private String authorName;
}
