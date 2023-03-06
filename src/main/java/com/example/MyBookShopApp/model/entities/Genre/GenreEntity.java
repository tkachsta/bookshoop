package com.example.MyBookShopApp.model.entities.Genre;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Book2Genre.Book2Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "genres")
@NoArgsConstructor
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
    @OneToMany(mappedBy = "parentId")
    private Set<GenreEntity> children;
    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Book2Genre> books;



}
