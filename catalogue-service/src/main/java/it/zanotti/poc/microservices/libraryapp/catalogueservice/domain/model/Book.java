package it.zanotti.poc.microservices.libraryapp.catalogueservice.domain.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * @author Michele Zanotti on 24/12/20
 **/
@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @ManyToMany(targetEntity = Author.class, cascade = CascadeType.MERGE)
    private List<Author> authors;
}
