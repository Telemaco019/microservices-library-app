package it.zanotti.poc.microservices.libraryapp.catalogueservice.api.web;

import com.google.common.collect.Lists;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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
    @NotNull
    private String subtitle;
    @NotNull
    private String description;
    private Integer pages;
    private String publisher;
    private LocalDate publishedDate;
}
