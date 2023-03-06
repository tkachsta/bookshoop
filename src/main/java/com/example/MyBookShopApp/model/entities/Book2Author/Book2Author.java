package com.example.MyBookShopApp.model.entities.Book2Author;
import com.example.MyBookShopApp.model.entities.Author.AuthorEntity;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "book2author")
public class Book2Author {
    @EmbeddedId
    private Book2AuthorKey book2AuthorKey;
    @ManyToOne
    @MapsId("author")
    @JoinColumn(name = "id_author")
    private AuthorEntity author;
    @ManyToOne
    @MapsId("book")
    @JoinColumn(name = "id_book")
    private BookEntity book;
    @Column(name = "sort_index")
    private Integer sortIndex;

}
