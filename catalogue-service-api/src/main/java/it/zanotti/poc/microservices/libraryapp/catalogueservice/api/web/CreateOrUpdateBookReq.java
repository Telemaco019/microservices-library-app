package it.zanotti.poc.microservices.libraryapp.catalogueservice.api.web;

import com.google.common.collect.Lists;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Michele Zanotti on 24/12/20
 **/
@Data
public class CreateOrUpdateBookReq {
    private String title;
    private List<Integer> authors = Lists.newArrayList();
    private String subtitle;
    private Integer pages;
    private String publisher;
    private String description;
    private LocalDate publishedDate;
}
