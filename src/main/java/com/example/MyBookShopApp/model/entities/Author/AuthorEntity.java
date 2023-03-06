package com.example.MyBookShopApp.model.entities.Author;
import com.example.MyBookShopApp.model.entities.Book2Author.Book2Author;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "authors")
@ToString
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_author")
    private Integer authorId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column
    private String photo;
    @Column
    private String slug;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonSerialize(using = BookSetSerializer.class)
    Set<Book2Author> books;

}
