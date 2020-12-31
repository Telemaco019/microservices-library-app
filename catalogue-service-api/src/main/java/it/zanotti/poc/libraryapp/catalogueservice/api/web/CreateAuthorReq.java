package it.zanotti.poc.libraryapp.catalogueservice.api.web;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Michele Zanotti on 24/12/20
 **/
@Data
public class CreateAuthorReq {
    @NotNull
    private String authorName;
}
