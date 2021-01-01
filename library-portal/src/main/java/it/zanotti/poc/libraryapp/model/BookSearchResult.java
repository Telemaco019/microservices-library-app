package it.zanotti.poc.libraryapp.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Michele Zanotti on 31/12/20
 **/
@Data
public class BookSearchResult {
    @SerializedName("id")
    private Integer bookId;
    @SerializedName("title")
    private String bookTitle;
    @SerializedName("subtitle")
    private String bookSubtitle;
    @SerializedName("description")
    private String bookDescription;
    @SerializedName("authors")
    private List<String> bookAuthors;
    @SerializedName("publicationDate")
    private LocalDate publicationDate;

    public String getAuthorsAsString() {
        return String.join(", ", bookAuthors);
    }
}
