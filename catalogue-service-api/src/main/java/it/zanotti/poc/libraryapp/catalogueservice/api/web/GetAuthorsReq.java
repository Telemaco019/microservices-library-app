package it.zanotti.poc.libraryapp.catalogueservice.api.web;

import lombok.Data;

/**
 * @author Michele Zanotti on 24/12/20
 **/
@Data
public class GetAuthorsReq {
    private Integer limit;
    private Integer offset;
}
