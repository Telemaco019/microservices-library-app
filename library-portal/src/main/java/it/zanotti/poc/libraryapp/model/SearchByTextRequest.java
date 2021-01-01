package it.zanotti.poc.libraryapp.model;

import lombok.Data;

/**
 * @author Michele Zanotti on 31/12/20
 **/
@Data
public class SearchByTextRequest {
    private final String text;
    private final Integer limit;
    private final Integer offset;

    public SearchByTextRequest(String text, Integer limit, Integer offset) {
        this.text = text;
        this.limit = limit;
        this.offset = offset;
    }
}
