package it.zanotti.poc.libraryapp.catalogueservice.api.web;

import com.google.common.collect.Lists;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Michele Zanotti on 27/12/20
 **/
@Data
public class UpdateBookReq {
    @NotNull
    private Integer id;
    private String title;
    private List<Integer> authors = Lists.newArrayList();
    private String subtitle;
    private String description;
    private Integer pages;
    private String publisher;
    private LocalDate publishedDate;
}
