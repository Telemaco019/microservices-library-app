package it.zanotti.poc.microservices.libraryapp.catalogueservice.api.web;

import lombok.Data;

/**
 * @author Michele Zanotti on 24/12/20
 **/
@Data
public class CreateOrUpdateAuthorReq {
    private Integer authorId;
    private String authorName;
}
