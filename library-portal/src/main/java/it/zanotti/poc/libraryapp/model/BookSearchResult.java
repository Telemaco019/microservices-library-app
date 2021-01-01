package it.zanotti.poc.libraryapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.compress.utils.Lists;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Michele Zanotti on 31/12/20
 **/
@Data
public class BookSearchResult {
    @JsonProperty("id")
    private Integer bookId;
    @JsonProperty("title")
    private String bookTitle;
    @JsonProperty("subtitle")
    private String bookSubtitle;
    @JsonProperty("description")
    private String bookDescription;
    private List<String> bookAuthors = Lists.newArrayList();
    private LocalDate publicationDate;

    public String getAuthorsAsString() {
        return String.join(", ", bookAuthors);
    }
}
