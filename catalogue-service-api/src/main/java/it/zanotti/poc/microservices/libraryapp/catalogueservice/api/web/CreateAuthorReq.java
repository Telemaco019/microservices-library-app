package it.zanotti.poc.microservices.libraryapp.catalogueservice.api.web;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Michele Zanotti on 24/12/20
 **/
@Data
public class CreateAuthorReq {
    @NotNull
    private String authorName;
}
