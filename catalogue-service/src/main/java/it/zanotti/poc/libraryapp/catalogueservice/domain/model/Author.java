package it.zanotti.poc.libraryapp.catalogueservice.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Michele Zanotti on 24/12/20
 **/
@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public Author(Integer id) {
        this.id = id;
    }

    public Author() {

    }
}
