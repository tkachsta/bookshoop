package com.example.MyBookShopApp.model.Author;
import com.example.MyBookShopApp.model.Book.BookEntity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "authors")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_author")
    private Integer authorId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String description;
    @Column
    private String photo;
    @Column
    private String slug;

}
