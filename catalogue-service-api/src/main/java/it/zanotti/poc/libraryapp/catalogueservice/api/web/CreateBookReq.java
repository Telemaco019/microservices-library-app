package it.zanotti.poc.libraryapp.catalogueservice.api.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Michele Zanotti on 24/12/20
 **/
@Data
public class CreateBookReq {
    @NotNull
    private String title;
    @NotEmpty
    private List<Integer> authors = Lists.newArrayList();
    private String subtitle;
    private String description;
    private Integer pages;
    private String publisher;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;
}
