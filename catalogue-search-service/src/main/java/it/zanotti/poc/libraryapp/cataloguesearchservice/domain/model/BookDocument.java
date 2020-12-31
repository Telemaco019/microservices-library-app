package it.zanotti.poc.libraryapp.cataloguesearchservice.domain.model;

import it.zanotti.poc.libraryapp.commons.AppConsts;
import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;

/**
 * @author Michele Zanotti on 26/12/20
 **/
@Data
@SolrDocument(collection = AppConsts.SOLR_BOOK_CORE_NAME)
public class BookDocument {
    @Field
    private Integer id;
    @Field
    private String title;
    @Field
    private String subtitle;
    @Field
    private String description;
    @Field
    private List<String> authors;
}
