package com.example.MyBookShopApp.model.entities.Book;
import com.example.MyBookShopApp.model.entities.Book2Author.Book2Author;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private Integer bookId;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer discount;
    @Column
    private String image;
    @Column(name = "is_bestseller", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isBestseller;
    @Column
    private Integer price;
    @Column
    private LocalDateTime pub_date;
    @Column
    private String slug;
    @Column
    private String title;
    @Column(name = "price_old")
    private Integer priceOld;
    @JsonSerialize(using = AuthorsStringSerializer.class)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Book2Author> authors;

}
