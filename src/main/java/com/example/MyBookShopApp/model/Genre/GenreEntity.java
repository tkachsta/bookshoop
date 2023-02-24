package com.example.MyBookShopApp.model.Genre;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "genres")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre")
    private Long genreId;
    @Column
    private String name;
    @Column(name = "parent_id")
    private Integer parentId;
    @Column
    private String slug;
}
